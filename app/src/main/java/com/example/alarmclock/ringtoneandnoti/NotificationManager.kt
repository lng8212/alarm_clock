package com.example.alarmclock.ringtoneandnoti

import android.annotation.SuppressLint
import android.app.*
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.alarmclock.MainActivity
import com.example.alarmclock.R
import com.example.alarmclock.Ringing

class NotificationManager : Service() {
    val CHANNEL_ID = "ALARM CHANNEL"
    var id: Int = 0




    @SuppressLint("WrongConstant")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        var mediaPlayer = MediaPlayer.create(this, R.raw.clock)
        var handleAlarm:String? = intent?.extras?.getString("handleAlarm")
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(
            NotificationChannel(
                CHANNEL_ID,
                "Notification",
                NotificationManager.IMPORTANCE_LOW

            )
        )
        if(handleAlarm == "on") {
            id =1
        }
        else if(handleAlarm=="off"){
            id=0
        }
        if(id == 1){

            var notifyIntent = Intent(this, Ringing::class.java)
            val notifyPendingIntent = PendingIntent.getActivity(this, 0, notifyIntent, 0)
            val notification = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_access_alarms_24)
                .setContentTitle("Báo thức")
                .setContentText("WAKE UP NOW!!!")
                .setContentIntent(notifyPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
            with(NotificationManagerCompat.from(this)) {
                notify(101, notification.build())
            }

            mediaPlayer.isLooping = true
            mediaPlayer.start()
            id = 0
        }
        else if (id ==0){
            mediaPlayer.stop()
            mediaPlayer.reset()
        }
        return START_NOT_STICKY

    }


    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }


}