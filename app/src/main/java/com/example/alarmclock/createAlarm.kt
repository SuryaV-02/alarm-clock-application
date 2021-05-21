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
                    Toast.makeText(
                            applicationContext,
                            "Selected item " +
                                    "" + alarmTypeList[position], Toast.LENGTH_SHORT
                    ).show()

                    if(position==1){
                        val description = findViewById<EditText>(R.id.description)
                        description.visibility = View.VISIBLE
                        pose=1
                    }else{
                        val description = findViewById<EditText>(R.id.description)
                        description.visibility = View.GONE
                        pose=0
                    }
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
        val description = findViewById<EditText>(R.id.description)
        val now = Calendar.getInstance()
        val alarm = Calendar.getInstance()
        alarm[Calendar.HOUR_OF_DAY] = time_picker.hour
        alarm[Calendar.MINUTE] = time_picker.minute
        if (alarm.before(now)){
            alarm.add(Calendar.DAY_OF_MONTH, 1)
        } //Add 1 day if time selected before now
        var i = Intent(applicationContext, myBroadcastReceiver::class.java)
        i.putExtra("labelText","${labelText.text}")
        if(pose==1){
            i.putExtra("desc",  "${description.text}")
        }
        var pendingIntent = PendingIntent.getBroadcast(applicationContext, 111, i, 0)
        var alarmManager : AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.set(AlarmManager.RTC_WAKEUP, alarm.timeInMillis, pendingIntent)
        val hour = alarm.get(Calendar.HOUR_OF_DAY)
        val minute = alarm.get(Calendar.MINUTE)
        val am_pm = if(hour<12) "AM"
        else "PM"
        Toast.makeText(this, "Alarm set for ${hour%12} : $minute $am_pm", Toast.LENGTH_SHORT).show()
        finish()
    }

    fun remainderTypeChosen(){

    }
    companion object{
        var is_important : Boolean = false
        var pose = 0
    }
    
}