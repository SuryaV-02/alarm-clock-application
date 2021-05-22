package com.example.alarmclock

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class after_notification : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_after_notification)
        val title = findViewById<TextView>(R.id.title)
        val descriptionArea = findViewById<TextView>(R.id.descriptionArea)
        val stopButton = findViewById<Button>(R.id.stopButton)
        title.text = intent.getStringExtra("labelText").toString()
        descriptionArea.text = intent.getStringExtra("desc").toString()
        stopButton.setOnClickListener {
//            val snackbar = Snackbar.make(
//                View(), "Replace with your own action",
//                Snackbar.LENGTH_LONG).setAction("Action", null)
//            snackbar.setActionTextColor(Color.BLUE)
//            val snackbarView = snackbar.view
//            snackbarView.setBackgroundColor(Color.LTGRAY)
//            val textView =
//                snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
//            textView.setTextColor(Color.BLUE)
//            textView.textSize = 28f
//            snackbar.show()
            Toast.makeText(this, "Remainder done!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}