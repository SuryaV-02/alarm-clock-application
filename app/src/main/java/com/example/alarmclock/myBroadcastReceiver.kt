package com.example.alarmclock

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent


class myBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (createAlarm.FULL_SCREEN_ACTION == intent?.action){
            createAlarm.CreateFullScreenNotification(context,intent)
        }
//            var i = Intent(context, alarmRinging::class.java)
//            val newLabel = intent?.getStringExtra("labelText").toString()
//            i.putExtra("labelText",newLabel)
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            context?.startActivity(i)

    }
}