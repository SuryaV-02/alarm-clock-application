package com.example.alarmclock

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import java.text.SimpleDateFormat
import java.util.*



class alarmRinging : AppCompatActivity() {

    companion object{

    }
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

        var SNOOZE_TIME: Long = 2*60*1000 //mins -> milli seconds

        var mp = MediaPlayer.create(applicationContext, R.raw.realme_tune)
        mp.isLooping = true
        mp.start()
        val stopButton = findViewById<Button>(R.id.stopButton)
        val snoozeButton = findViewById<Button>(R.id.snoozeButton)
        val sdf = SimpleDateFormat("hh:mm")
        val currentTime = sdf.format(Date()).toString()
        snoozeButton.text = intent.getStringExtra("labelText")
        stopButton.setOnClickListener {
            mp.stop()
            finish()
        }
        snoozeButton.setOnClickListener {
            mp.stop()
            var i = Intent(applicationContext, myBroadcastReceiver::class.java)
            var pendingIntent = PendingIntent.getBroadcast(applicationContext, 111, i, 0)
            var alarmManager : AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + SNOOZE_TIME, pendingIntent)
            Toast.makeText(this, "Snooze for 2 mins", Toast.LENGTH_SHORT).show()
            finish()
        }
    }




}