package com.example.task14

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            val notificationManager: NotificationManager
            val intent = Intent(this.getApplicationContext(), MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
            val builder = NotificationCompat.Builder(this, "notify_001")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.snow)
                .setContentTitle("Напоминание")
                .setContentText("Пора покормить кота!")
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setLargeIcon(
                    BitmapFactory.decodeResource(getResources(), R.drawable.cat))
                .setAutoCancel(true)

            val mContext = this
            notificationManager = mContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channelId = "Your_channel_id"
                val channel = NotificationChannel(
                    channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_HIGH
                )
                notificationManager.createNotificationChannel(channel)
                builder.setChannelId(channelId)
            }
            notificationManager.notify(0, builder.build())
        }

    }
}

