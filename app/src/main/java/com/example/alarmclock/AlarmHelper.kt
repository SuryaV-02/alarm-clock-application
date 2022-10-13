package com.example.alarmclock

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat

class AlarmHelper {
    companion object{
        fun cancelAlarm(id: Int, context: Context){
            val intent = Intent(createAlarm.FULL_SCREEN_ACTION, null, context, myBroadcastReceiver::class.java)
            PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT).cancel()
            Log.i("SKHST_961","Cancelled alarm $id successfully!")
        }
        @RequiresApi(Build.VERSION_CODES.M)
        fun createAlarmPendingIntent(alarmSchedule: AlarmSchedule, context:Context) {
            val intent = Intent(createAlarm.FULL_SCREEN_ACTION, null, context, myBroadcastReceiver::class.java)
            intent.putExtra("alarmID",alarmSchedule.id)
            intent.putExtra("alarmLabel",alarmSchedule.label)
            val pendingIntent =
                PendingIntent.getBroadcast(context, alarmSchedule.id!!.toInt(), intent, PendingIntent.FLAG_UPDATE_CURRENT)
            val alarmManager = context.getSystemService(AppCompatActivity.ALARM_SERVICE) as AlarmManager
            alarmManager?.set(
                AlarmManager.RTC_WAKEUP,
                alarmSchedule.getMilliSecs()!!,
                pendingIntent
            )
            NotificationManagerCompat.from(context)
                .cancel(createAlarm.NOTIFICATION_ID) //cancel last notification for repeated tests
           createAlarm.dbHelper!!.getAllAlarmSchedules().size
        }
        fun showToast(context : Context, message : String){
            Toast.makeText(context, "$message", Toast.LENGTH_SHORT).show()
        }
    }
}