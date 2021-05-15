package com.example.alarmclock

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class alarmRinging : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm_ringing)
        var mp = MediaPlayer.create(applicationContext,R.raw.realme_tune)
        mp.start()
        val stopButton = findViewById<Button>(R.id.stopButton)
        val sdf = SimpleDateFormat("hh:mm")
        val currentTime = sdf.format(Date()).toString()
        stopButton.text = currentTime
        stopButton.setOnClickListener {
            mp.stop()
        }
    }
}