package com.example.alarmclock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast

class createAlarm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_alarm)
        val alarmTypeList = resources.getStringArray(R.array.alarmType)

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
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }
}