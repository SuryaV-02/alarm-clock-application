package com.example.alarmclock

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class createAlarm : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w = window
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }
        setContentView(R.layout.activity_create_alarm)
        val alarmTypeList = resources.getStringArray(R.array.alarmType)

        val time_picker = findViewById<TimePicker>(R.id.time_picker)
        val dd_alarmType = findViewById<Spinner>(R.id.dd_alarmType)
        val ctv_important = findViewById<CheckedTextView>(R.id.ctv_important)
        val ctv_wake_me_up = findViewById<CheckedTextView>(R.id.ctv_wake_me_up)
        val createAlarmButton = findViewById<Button>(R.id.createAlarmButton)
        val labelText = findViewById<EditText>(R.id.labelText)

        createAlarmButton.setOnClickListener {
            this.createAlarmNow()
        }

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
                    Toast.makeText(
                            applicationContext,
                            "Selected item " +
                                    "" + alarmTypeList[position], Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        if(ctv_important!=null){
            ctv_important.isChecked = false
        }
        ctv_important.setOnClickListener {
            ctv_important.isChecked = !ctv_important.isChecked
            // TODO: 19-May-21  
            is_important = true
            Toast.makeText(this, "${is_important.toString()}", Toast.LENGTH_SHORT).show()
        }
        
    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun createAlarmNow(){
        val time_picker = findViewById<TimePicker>(R.id.time_picker)
        val now = Calendar.getInstance()
        val alarm = Calendar.getInstance()
        alarm[Calendar.HOUR_OF_DAY] = time_picker.hour
        alarm[Calendar.MINUTE] = time_picker.minute
        if (alarm.before(now)){
            alarm.add(Calendar.DAY_OF_MONTH, 1)
        } //Add 1 day if time selected before now
        var i = Intent(applicationContext, myBroadcastReceiver::class.java)
        var pendingIntent = PendingIntent.getBroadcast(applicationContext, 111, i, 0)
        var alarmManager : AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.set(AlarmManager.RTC_WAKEUP, alarm.timeInMillis, pendingIntent)
        val hour = alarm.get(Calendar.HOUR_OF_DAY)
        val minute = alarm.get(Calendar.MINUTE)
        val am_pm = if(hour<12) "AM"
        else "PM"
        Toast.makeText(this, "Alarm set for ${hour%12} : $minute : $am_pm", Toast.LENGTH_SHORT).show()
        finish()
    }

    companion object{
        var is_important : Boolean = false
    }
    
}