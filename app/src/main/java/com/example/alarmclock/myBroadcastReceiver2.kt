package com.example.alarmclock

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.Intent.getIntent
import android.util.Log


class myBroadcastReceiver2 : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
            var i = Intent(context, after_notification::class.java)
        Log.i("location","@receiver2")
            val newLabel = intent?.getStringExtra("labelText").toString()
            val newDesc = intent?.getStringExtra("desc").toString()
            i.putExtra("labelText",newLabel)
            i.putExtra("desc",newDesc)
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context?.startActivity(i)
    }
}