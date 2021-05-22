package com.example.alarmclock

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.Intent.getIntent
import android.util.Log


class myBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val type = intent?.getStringExtra("type")
        intent?.removeExtra("type")
        Log.i("Type","@receive = $type")
        if(type=="A"){
            var i = Intent(context, alarmRinging::class.java)
            val newLabel = intent.getStringExtra("labelText").toString()
            i.putExtra("labelText",newLabel)
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context?.startActivity(i)
        }else{
            var i = Intent(context, after_notification::class.java)
            val newLabel = intent?.getStringExtra("labelText").toString()
            val newDesc = intent?.getStringExtra("desc").toString()
            i.putExtra("labelText",newLabel)
            i.putExtra("desc",newDesc)
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context?.startActivity(i)
        }
    }
}