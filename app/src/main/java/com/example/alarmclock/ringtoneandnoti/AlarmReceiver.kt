package com.example.alarmclock.ringtoneandnoti

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.alarmclock.MainActivity
import com.example.alarmclock.R
import com.example.alarmclock.mainFragment

class AlarmReceiver : BroadcastReceiver(){
    private val CHANNEL_ID = "channel_id_01"
    private val notificationID = 101
    private var noti = com.example.alarmclock.ringtoneandnoti.NotificationManager(CHANNEL_ID, notificationID)
    override fun onReceive(context: Context?, intent: Intent?) {
        val message = intent?.getStringExtra("Messenger")
            noti.createNotificationChannel(context)
            noti.sendNotification(context,"Báo thức", message.toString())


    }



}