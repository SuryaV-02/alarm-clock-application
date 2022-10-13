package com.example.alarmclock

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RemoteViews
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class after_notification : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("location","@after_notification")
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

//            lateinit var notificationManager: NotificationManager
//            lateinit var notificationChannel: NotificationChannel
//            lateinit var builder: Notification.Builder
//            val channelId = "i.apps.notifications"
//            val description = "Test notification"
//            var i = Intent(applicationContext, after_notification::class.java)
//            var pendingIntent = PendingIntent.getBroadcast(applicationContext, 111, i, 0)
//           notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            val contentView = RemoteViews(packageName, R.layout.activity_after_notification)
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                notificationChannel = NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
//                notificationChannel.enableLights(true)
//                notificationChannel.lightColor = Color.GREEN
//                notificationChannel.enableVibration(false)
//                notificationManager.createNotificationChannel(notificationChannel)
//
//                builder = Notification.Builder(this, channelId)
//                    .setContent(contentView)
//                    .setSmallIcon(R.drawable.ic_launcher_background)
//                    .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.ic_launcher_background))
//                    .setContentIntent(pendingIntent)
//            }else {
//
//                builder = Notification.Builder(this)
//                    .setContent(contentView)
//                    .setSmallIcon(R.drawable.ic_launcher_background)
//                    .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.ic_launcher_background))
//                    .setContentIntent(pendingIntent)
//            }
//            notificationManager.notify(1234, builder.build())


//            val intent = Intent(this, LauncherActivity::class.java)
//            val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                notificationChannel = NotificationChannel(channelId, description, NotificationManager .IMPORTANCE_HIGH)
//                notificationChannel.lightColor = Color.BLUE notificationChannel.enableVibration(true)
//                notificationManager.createNotificationChannel(notificationChannel)
//                builder = Notification.Builder(this, channelId).setContentTitle("NOTIFICATION USING " +
//                        "KOTLIN").setContentText("Test Notification").setSmallIcon(R.drawable.ic_brightness).setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable
//                    .ic_launcher_background)).setContentIntent(pendingIntent)
//            }
//            notificationManager.notify(12345, builder.build())

            Toast.makeText(this, "Remainder done!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}