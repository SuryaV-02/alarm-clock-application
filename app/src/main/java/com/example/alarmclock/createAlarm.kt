package com.example.alarmclock

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService


class createAlarm : AppCompatActivity(){

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_alarm_ringing)
        createNotificationChannel(this)

        //set flags so activity is showed when phone is off (on lock screen)
        window.addFlags(
            WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                    or WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                    or WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w = window
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }
        setContentView(R.layout.activity_create_alarm)
        val alarmTypeList = resources.getStringArray(R.array.alarmType)
        val createAlarmButton = findViewById<Button>(R.id.createAlarmButton)

        val time_picker = findViewById<TimePicker>(R.id.time_picker)

        createAlarmButton.setOnClickListener {
            this.createAlarmNow()
        }
        val dd_alarmType = findViewById<Spinner>(R.id.dd_alarmType)
        if (dd_alarmType != null) {
            val adapter = ArrayAdapter(
                    this,
                    android.R.layout.simple_spinner_item, alarmTypeList
            )
            dd_alarmType.adapter = adapter
            dd_alarmType.onItemSelectedListener = object :
                    AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View, position: Int, id: Long
                ) {
//                    Toast.makeText(
//                            applicationContext,
//                            "Selected item " +
//                                    "" + alarmTypeList[position], Toast.LENGTH_SHORT
//                    ).show()

                    if(position==1){
                        val description = findViewById<EditText>(R.id.description)
                        description.visibility = View.VISIBLE
                        pose=1
                    }else{
                        val description = findViewById<EditText>(R.id.description)
                        description.visibility = View.GONE
                        pose=0
                    }
//                    Toast.makeText(applicationContext, "POSE : $pose", Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun createAlarmNow(){
        val labelText = findViewById<EditText>(R.id.labelText)
        val time_picker = findViewById<TimePicker>(R.id.time_picker)
        val descriptionField = findViewById<EditText>(R.id.description)
        val now = Calendar.getInstance()
        val alarm = Calendar.getInstance()
        alarm[Calendar.HOUR_OF_DAY] = time_picker.hour
        alarm[Calendar.MINUTE] = time_picker.minute
        if (alarm.before(now)){
            alarm.add(Calendar.DAY_OF_MONTH, 1)
        } //Add 1 day if time selected before now


        // IN case of remainder
//        if(pose==1){
////            Toast.makeText(applicationContext, "remainder selected", Toast.LENGTH_SHORT).show()
////            var i = Intent(applicationContext, myBroadcastReceiver2::class.java)
////            i.putExtra("labelText","${labelText.text}")
////            i.putExtra("desc",  "${descriptionField.text}")
////            val type = "R"
////            Log.i("Type","@create = $type")
////            i.putExtra("type","$type")
////            var pendingIntent = PendingIntent.getBroadcast(applicationContext, 111, i, 0)
////            var alarmManager : AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
////            alarmManager.set(AlarmManager.RTC_WAKEUP, 5000, pendingIntent)
//            Toast.makeText(this,"remainder type", Toast.LENGTH_SHORT).show()
//            var i = Intent(applicationContext, myBroadcastReceiver::class.java)
//            i.putExtra("labelText","${labelText.text}")
//            i.removeExtra("type")
//            val type = "A"
//            Log.i("Type","@create = $type")
//            i.putExtra("type","$type")
//            var pendingIntent = PendingIntent.getBroadcast(applicationContext, 111, i, 0)
//            var alarmManager : AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
//            alarmManager.set(AlarmManager.RTC_WAKEUP, alarm.timeInMillis, pendingIntent)
//            val hour = alarm.get(Calendar.HOUR_OF_DAY)
//            val minute = alarm.get(Calendar.MINUTE)
//            val am_pm = if(hour<12) "AM"
//            else "PM"
//            Toast.makeText(this, "Alarm set for ${hour%12} : $minute $am_pm", Toast.LENGTH_SHORT).show()
//            finish()
//        }
        // IN case of Alarm
//        else{
           // Toast.makeText(this,"Alarm type", Toast.LENGTH_SHORT).show()
//            var i = Intent(applicationContext, myBroadcastReceiver::class.java)
//            i.putExtra("labelText","${labelText.text}")
//            i.removeExtra("type")
//            val type = "A"
//            Log.i("Type","@create = $type")
//            i.putExtra("type","$type")
//            var pendingIntent = PendingIntent.getBroadcast(applicationContext, 111, i, 0)
//            var alarmManager : AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
//            alarmManager.set(AlarmManager.RTC_WAKEUP, alarm.timeInMillis, pendingIntent)
//            val hour = alarm.get(Calendar.HOUR_OF_DAY)
//            val minute = alarm.get(Calendar.MINUTE)
//            val am_pm = if(hour<12) "AM"
//            else "PM"
//            Toast.makeText(this, "Alarm set for ${hour%12} : $minute $am_pm", Toast.LENGTH_SHORT).show()
            userMessage = labelText.text.toString()
            buttonClick(alarm)
            finish()
//        }
    }

    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = NotificationManagerCompat.from(context)
            if (notificationManager.getNotificationChannel(CHANNEL_ID) == null) {
                val channel = NotificationChannel(
                    CHANNEL_ID,
                    "channel_name",
                    NotificationManager.IMPORTANCE_HIGH
                )
                channel.description = "channel_description"
                notificationManager.createNotificationChannel(channel)
            }
        }
    }

    fun buttonClick(alarm: Calendar) {
        val intent = Intent(FULL_SCREEN_ACTION, null, this, myBroadcastReceiver::class.java)
        val pendingIntent =
            PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val alarmManager = this.getSystemService(ALARM_SERVICE) as AlarmManager
        alarmManager?.set(
            AlarmManager.RTC_WAKEUP,
            alarm.timeInMillis,
            pendingIntent
        )
        NotificationManagerCompat.from(this)
            .cancel(NOTIFICATION_ID) //cancel last notification for repeated tests
    }

    companion object{
        var is_important : Boolean = false
        var pose = 0
        val CHANNEL_ID = "my_channel"
        val FULL_SCREEN_ACTION = "full_screen_action"
        val NOTIFICATION_ID = 1

        var userMessage : String = "Time's up..."

        fun CreateFullScreenNotification(context: Context?, intentParam: Intent) {
            val intent = Intent(context, alarmRinging::class.java)
            intent.putExtra("user-custom_message",userMessage)
            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_NO_USER_ACTION or Intent.FLAG_ACTIVITY_SINGLE_TOP
            val pendingIntent =
                PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            val notificationBuilder = NotificationCompat.Builder(context!!, CHANNEL_ID)
            .setSmallIcon(R.drawable.clock)
                .setContentTitle("Full Screen Alarm Test")
                .setContentText("This is a test")
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setCategory(NotificationCompat.CATEGORY_ALARM)
                .setFullScreenIntent(pendingIntent, true)
                .setContentIntent(pendingIntent)
                .setNotificationSilent()
            NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, notificationBuilder.build())

//            var alarmManager : AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
//            alarmManager.set(AlarmManager.RTC_WAKEUP, 5000, pendingIntent)
        }
    }

}