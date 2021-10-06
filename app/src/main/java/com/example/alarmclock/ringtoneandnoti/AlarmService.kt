package com.example.alarmclock.ringtoneandnoti

import android.annotation.SuppressLint
import android.app.*
import android.app.NotificationManager
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.alarmclock.R
import com.example.alarmclock.activity.Ringing

class AlarmService : Service() {
    val CHANNEL_ID = "ALARM CHANNEL"
    lateinit var mediaPlayer: MediaPlayer
    var id:Int =0
    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    @SuppressLint("WrongConstant")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(): String {
        val name = "channel 1"
        val des = "báo thức"
        val mChannel = NotificationChannel("100",name,NotificationManager.IMPORTANCE_DEFAULT)
            mChannel.description = des
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(mChannel)
        return "100"

    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
       //tạo mediaPlayer
        mediaPlayer = MediaPlayer.create(this, R.raw.clock)
        var handleAlarm:String? = intent?.extras?.getString("handleAlarm1")
        if(handleAlarm == "on") {
            id =1
        }
        else if(handleAlarm=="off"){
            id=0
        }

        if(id==1) {
            mediaPlayer.isLooping = true
            val channel_id = createNotificationChannel()
            var notifyIntent = Intent(this, Ringing::class.java) //tạo intent đến Ringing
            val notifyPendingIntent = PendingIntent.getActivity(this, 0, notifyIntent, 0)
            val notification: Notification = Notification.Builder(this, channel_id) //tạo thông báo
                .setSmallIcon(R.drawable.ic_baseline_access_alarms_24)
                .setContentTitle("Báo thức")
                .setContentText("WAKE UP NOW!!!")
                .setContentIntent(notifyPendingIntent)
                .build()
            startForeground(101,notification)
            mediaPlayer.start()
            id = 0
        }
        else if(id==0)
        {
            mediaPlayer.stop()
            mediaPlayer.reset()
            stopForeground(true)

        }
        return START_STICKY // yêu cầu hệ thống tạo lại service khi bị OS huỷ lúc quá tải.

    }

    override fun onDestroy() {
        super.onDestroy()
        stopForeground(true)
        mediaPlayer.stop()
        mediaPlayer.release()
    }

}