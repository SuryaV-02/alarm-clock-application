package com.example.alarmclock

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    @SuppressLint("NewApi")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var selectedCalendarMillis : Long = 0
        var currentCalendarMillis : Long = 0
        var hours24CalendarMillis : Long = 0
        val mTimePicker: TimePickerDialog

        val mcurrentCalender = Calendar.getInstance()
        val hour = mcurrentCalender.get(Calendar.HOUR_OF_DAY)
        val minute = mcurrentCalender.get(Calendar.MINUTE)
        currentCalendarMillis = mcurrentCalender.timeInMillis


        val m24HoursTime = Calendar.getInstance()
        m24HoursTime[Calendar.HOUR_OF_DAY] = 24
        m24HoursTime[Calendar.MINUTE] = 0
        m24HoursTime[Calendar.SECOND] = 0
        m24HoursTime[Calendar.MILLISECOND] = 0
        hours24CalendarMillis = mcurrentCalender.timeInMillis


        mTimePicker = TimePickerDialog(this, object : TimePickerDialog.OnTimeSetListener {
            override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                val selectedCalendar = Calendar.getInstance()
                selectedCalendar[Calendar.HOUR_OF_DAY] = hourOfDay
                selectedCalendar[Calendar.MINUTE] = minute
                selectedCalendar[Calendar.SECOND] = 0
                selectedCalendar[Calendar.MILLISECOND] = 0
                selectedCalendarMillis = selectedCalendar.timeInMillis
                if (mcurrentCalender.get(Calendar.HOUR_OF_DAY) > selectedCalendar[Calendar.HOUR_OF_DAY]) {
                    selectedCalendarMillis += (hours24CalendarMillis - currentCalendarMillis)
                    Toast.makeText(applicationContext, "Alarm set for Tomorrow $hourOfDay : $minute", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(applicationContext, "Alarm set!", Toast.LENGTH_SHORT).show()
                }
            }
        }, hour, minute, false)

        val selectTime = findViewById<Button>(R.id.selectTime)
        selectTime.setOnClickListener {
            mTimePicker.show()
        }
        val startButton = findViewById<Button>(R.id.startButton)
        startButton.setOnClickListener {
            var i = Intent(applicationContext, myBroadcastReceiver::class.java)
            var pendingIntent = PendingIntent.getBroadcast(applicationContext, 111, i, 0)
            var alarmManager : AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.set(AlarmManager.RTC_WAKEUP,selectedCalendarMillis, pendingIntent)
        }
    }
}