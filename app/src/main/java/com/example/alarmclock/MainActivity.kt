package com.example.alarmclock

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
            startActivity(intent)
        }
        dbHelper = SqliteOpenHelper(this,null)
        val alarmSchedules = dbHelper!!.getAllAlarmSchedules()
        val rv_alarmSchedules = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.rv_alarmSchedules)
        rv_alarmSchedules.layoutManager = LinearLayoutManager(this)
        val alarmScheduleAdapter = AlarmScheduleAdapter(this, ArrayList(alarmSchedules))
        Log.i("SKHST_57", alarmSchedules.toString())
        rv_alarmSchedules.adapter = alarmScheduleAdapter
    }
}