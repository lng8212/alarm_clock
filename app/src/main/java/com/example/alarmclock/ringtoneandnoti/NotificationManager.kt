package com.example.alarmclock.ringtoneandnoti

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.alarmclock.MainActivity
import com.example.alarmclock.R

class NotificationManager constructor(val CHANNEL_ID: String,val notificationID: Int) {
    lateinit var mediaPlayer: MediaPlayer
    fun createNotificationChannel(context: Context?){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            val name = "Notification"
            val descriptionText = "Notification Description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID,name,importance).apply {
                description = descriptionText
            }
            val notificationManager = context!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
    fun sendNotification(context: Context?,title:String, descrip: String){
        mediaPlayer = MediaPlayer.create(context, R.raw.clock)

        val intent = Intent(context!!, MainActivity::class.java).apply {

            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(context!!,0,intent,0)

        val builder = NotificationCompat.Builder(context!!,CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_access_alarms_24)
            .setContentTitle(title)
            .setContentText(descrip)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        with(NotificationManagerCompat.from(context!!)){
            notify(notificationID,builder.build())
        }
        mediaPlayer.start()
    }
}