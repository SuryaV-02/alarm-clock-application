package com.example.alarmclock

import android.R.attr
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager


class MainActivity : AppCompatActivity() {
    @SuppressLint("NewApi")
    @RequiresApi(Build.VERSION_CODES.M)
    companion object{
        var dbHelper : SqliteOpenHelper? = null
        var alarmScheduleAdapter : AlarmScheduleAdapter? = null
        val CREATE_ALARM_ACIVITY_REQUEST_CODE = 122
        var alarmSchedules : ArrayList<AlarmSchedule>? = null
    }
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w = window
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }
        setContentView(R.layout.activity_main)
        val createAlarmButton = findViewById<Button>(R.id.createAlarm)
        createAlarmButton.setOnClickListener {
            val intent = Intent(this, createAlarm::class.java)
            startActivityForResult(intent, CREATE_ALARM_ACIVITY_REQUEST_CODE)
        }
        dbHelper = SqliteOpenHelper(this,null)
        alarmSchedules = ArrayList(dbHelper!!.getAllAlarmSchedules())
        val rv_alarmSchedules = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.rv_alarmSchedules)
        rv_alarmSchedules.layoutManager = LinearLayoutManager(this)
        alarmScheduleAdapter = AlarmScheduleAdapter(this, alarmSchedules!!)
        Log.i("SKHST_57", alarmSchedules.toString())
        rv_alarmSchedules.adapter = alarmScheduleAdapter
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === CREATE_ALARM_ACIVITY_REQUEST_CODE && resultCode === RESULT_OK) {
            alarmSchedules!!.clear()
            alarmSchedules!!.addAll(dbHelper!!.getAllAlarmSchedules())
            alarmScheduleAdapter!!.notifyDataSetChanged()
        }
    }

}