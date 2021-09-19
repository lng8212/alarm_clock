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
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context,"Alarm Ringing",Toast.LENGTH_LONG).show()
        val message = intent?.getStringExtra("Messenger")

            createNotificationChannel(context)
            sendNotification(context,message.toString())


    }
    private fun createNotificationChannel(context: Context?){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
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
    private fun sendNotification(context: Context?, descrip: String){
        val intent = Intent(context!!, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(context!!,0,intent,0)

        val builder = NotificationCompat.Builder(context!!,CHANNEL_ID).setSmallIcon(R.drawable.ic_baseline_access_alarms_24)
            .setContentText("Báo thức")
            .setContentText("Báo thức lúc: " + descrip)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        with(NotificationManagerCompat.from(context!!)){
            notify(notificationID,builder.build())
        }
    }


}