package com.example.alarmclock

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*



class alarmRinging : AppCompatActivity() {
    companion object{
        var dbHelper : SqliteOpenHelper? = null
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN or
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_FULLSCREEN or
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
                        WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON)

        setContentView(R.layout.activity_alarm_ringing)
        setElementsData()
        dbHelper = SqliteOpenHelper(this,null)
        var SNOOZE_TIME: Long = 2*60*1000 //mins -> milli seconds

        var mp = MediaPlayer.create(applicationContext, R.raw.realme_tune)
        mp.isLooping = true
        mp.start()
        val stopButton = findViewById<Button>(R.id.stopButton)
        val snoozeButton = findViewById<Button>(R.id.snoozeButton)
        val sdf = SimpleDateFormat("hh:mm")
        val currentTime = sdf.format(Date()).toString()
        stopButton.setOnClickListener {
//            val latestSchedule = dbHelper!!.getLatestAlarmSchedule()
//            buttonClick(latestSchedule)
            Log.i("SKHST_432","Toggling alarm as it's stopped")
            dbHelper!!.toggleAlarmSchedule(intent.getStringExtra("alarmID").toString())
            mp.stop()
            finish()
        }
        snoozeButton.setOnClickListener {
            mp.stop()
            val sdf = SimpleDateFormat("hh:mm")
            val alarmID = intent.getStringExtra("alarmID")
            val alarmLabel = intent.getStringExtra("alarmLabel")
            val snoozeMillisecs : Long = System.currentTimeMillis() + SNOOZE_TIME
            val snoozeTime =  sdf.format(snoozeMillisecs).toString()
            var i = Intent(applicationContext, myBroadcastReceiver::class.java)
            i.putExtra("alarmLabel", alarmLabel)
            i.putExtra("alarmID", alarmID)
            var pendingIntent = PendingIntent.getBroadcast(applicationContext, intent.getStringExtra("alarmID")!!.toInt(), i, 0)
            var alarmManager : AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.set(AlarmManager.RTC_WAKEUP, snoozeMillisecs, pendingIntent)
            dbHelper!!.updateAlarmSchedule(alarmID,snoozeTime, alarmLabel, snoozeMillisecs,"ON")
            Toast.makeText(this, "Snooze for 2 mins", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setElementsData() {
        val tv_usr_custom_message = findViewById<TextView>(R.id.tv_usr_custom_message)
        val tv_alarm_ring_title = findViewById<TextView>(R.id.tv_alarm_ring_title)
        tv_alarm_ring_title.text = getCurrentTime("HH : mm")
        var message = intent.getStringExtra("alarmLabel")
        if(message.isNullOrEmpty()){
            message = "Alarm"
        }
        tv_usr_custom_message.text = message
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getCurrentTime(format: String): CharSequence? {
        val current = LocalDateTime.now()
        val formatter =DateTimeFormatter.ofPattern(format)
        val formatted = current.format(formatter)
        println("Current Date and Time is: $formatted")
        return formatted
    }

    fun buttonClick(alarmSchedule: AlarmSchedule) {
        val intent = Intent(createAlarm.FULL_SCREEN_ACTION, null, this, myBroadcastReceiver::class.java)
        val pendingIntent =
            PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val alarmManager = this.getSystemService(ALARM_SERVICE) as AlarmManager
        alarmManager?.set(
            AlarmManager.RTC_WAKEUP,
            alarmSchedule.getMilliSecs()!!,
            pendingIntent
        )
        NotificationManagerCompat.from(this)
            .cancel(createAlarm.NOTIFICATION_ID) //cancel last notification for repeated tests
    }


}