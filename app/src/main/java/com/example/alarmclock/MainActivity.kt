package com.example.alarmclock

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.Dialog
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    var hours =0
    var minutes =0
    @SuppressLint("NewApi")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        var mSelectedTime  = ""
//        var mSelectedMinute : Long = 0
//        var mSelectedHour: Long = 0
        var currentCalendarMillis : Long = 0

        val mTimePicker: TimePickerDialog
        val mcurrentTime = Calendar.getInstance()
        val hour = mcurrentTime.get(Calendar.HOUR_OF_DAY)
        val minute = mcurrentTime.get(Calendar.MINUTE)

        mTimePicker = TimePickerDialog(this, object : TimePickerDialog.OnTimeSetListener {
            override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
//                mSelectedTime = String.format("%d : %d", hourOfDay, minute)
//                mSelectedHour = hourOfDay.toLong()
//                mSelectedMinute = minute.toLong()
                val currentCalendar = Calendar.getInstance()
                currentCalendar[Calendar.HOUR_OF_DAY] = hourOfDay
                currentCalendar[Calendar.MINUTE] = minute
                currentCalendar[Calendar.SECOND] = 0
                currentCalendar[Calendar.MILLISECOND] = 0
                currentCalendarMillis = currentCalendar.timeInMillis
            }
        }, hour, minute, false)
//
//        val inputTimeInSeconds = findViewById<EditText>(R.id.inputTimeInSeconds)
        val selectTime = findViewById<Button>(R.id.selectTime)
        selectTime.setOnClickListener {
            mTimePicker.show()
        }

        val startButton = findViewById<Button>(R.id.startButton)
        startButton.setOnClickListener {
//            var mCurentHourFormat = SimpleDateFormat("HH")
//            var mCurentMinuteFormat = SimpleDateFormat("MM")
//
//            var mCurrentHour = mCurentHourFormat.format(Date()).toLong()
//            var mCurrentMinute = mCurentMinuteFormat.format(Date()).toLong()
//
//            var mCurrentTimeInMs = (mCurrentHour*60*60*1000) + (mCurrentMinute*60*1000)
//            var mSelectedTimeInMs  = (mSelectedHour*60*60) + (mSelectedMinute*60)
//
//            Log.i("mCurrentHour", "$mCurrentHour")
//            Log.i("mCurrentMinute", "$mCurrentMinute")
//            Log.i("mSelectedHour", "$mSelectedHour")
//            Log.i("mSelectedMinute", "$mSelectedMinute")
//            Log.i("mCurrentTimeInMs", "$mCurrentTimeInMs")
//            Log.i("mSelectedTimeInMs", "$mSelectedTimeInMs")
//
//            var differenceInMs = mSelectedTimeInMs - mCurrentTimeInMs

//
//            val cal = Calendar.getInstance()
//            cal.set(Calendar.YEAR,Calendar.MONTH,Calendar.DATE,mSelectedHour.toInt(),mSelectedMinute.toInt())
//            val ms = cal.timeInMillis
//
//            Log.i("Cal in ms :", "cal = $ms")

            var i = Intent(applicationContext, myBroadcastReceiver::class.java)
            var pendingIntent = PendingIntent.getBroadcast(applicationContext, 111, i, 0)
            var alarmManager : AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
//
//
//            val calendar: Calendar = Calendar.getInstance()
//            calendar.timeInMillis = System.currentTimeMillis()
//            calendar.add(Calendar.SECOND, 3)
//
//            val d = currentCalendarMillis
//
//
//
//            //alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)

            alarmManager.set(AlarmManager.RTC_WAKEUP,currentCalendarMillis, pendingIntent)
        }





//        startButton.setOnClickListener {
//            //var seconds = inputTimeInSeconds.text.toString().toInt()
//            var i = Intent(applicationContext, myBroadcastReceiver::class.java)
//            var pendingIntent = PendingIntent.getBroadcast(applicationContext,111,i,0)
//            var alarmManager : AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
//            val sdf = SimpleDateFormat("hh:mm")
//            val currentTime = sdf.format(Date()).toString()
//
//            var currentTimeInMs = System.currentTimeMillis()
//
//            var trigger_at_ms = (hours*60*60*1000) + (minutes*60*1000)
//
//            val difference = trigger_at_ms - currentTimeInMs
//
//            val actual = System.currentTimeMillis()+difference
//
//            alarmManager.set(AlarmManager.RTC_WAKEUP,actual,pendingIntent)
//            Toast.makeText(applicationContext, "Alarm set for ${actual%1000}", Toast.LENGTH_SHORT).show()
//        }

    }
     @RequiresApi(Build.VERSION_CODES.M)
     private fun OnClickTime() {
        val timePickerDialog = Dialog(this)
        timePickerDialog.setTitle("Select time")
        timePickerDialog.setContentView(R.layout.time_picker)

        val timePicker = timePickerDialog.findViewById<TimePicker>(R.id.TimeClock)
        val finishButton = timePickerDialog.findViewById<Button>(R.id.finishButton)

        timePicker.setOnTimeChangedListener { _, hour, minute -> var hour = hour
            Toast.makeText(this, "$hour : $minute", Toast.LENGTH_SHORT).show()
        }
         finishButton.setOnClickListener {
             hours = timePicker.hour
             minutes= timePicker.minute
             timePickerDialog.dismiss()
             Toast.makeText(this, "SRIKALAHASTHI", Toast.LENGTH_SHORT).show()
             Toast.makeText(this, "$hours : $minutes", Toast.LENGTH_LONG).show()
         }
         timePickerDialog.show()
    }
}