package com.example.alarmclock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class createAlarm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_alarm)
        val alarmTypeList = resources.getStringArray(R.array.alarmType)

        val dd_alarmType = findViewById<Spinner>(R.id.dd_alarmType)
        val ctv_important = findViewById<CheckedTextView>(R.id.ctv_important)
        val ctv_wake_me_up = findViewById<CheckedTextView>(R.id.ctv_wake_me_up)

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
    companion object{
        var is_important : Boolean = false
    }
    
}