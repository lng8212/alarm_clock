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
import java.util.*

class AlarmReceiver : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.e("Receiver", "Came")
        var handleAlarm : String? = intent?.extras?.getString("handleAlarm")
        var myIntent = Intent(context, com.example.alarmclock.ringtoneandnoti.NotificationManager::class.java)
        myIntent.putExtra("handleAlarm",handleAlarm)
        if(intent?.getBooleanExtra("Repeat",true) == true)
        {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                context?.startForegroundService(myIntent)
            }
            else{
                 context?.startService(myIntent)
            }
        }
        else
        {
            if(today(intent))
            {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    context?.startForegroundService(myIntent)
                }
                else{
                    context?.startService(myIntent)
                }
            }
        }

    }
    private fun today(intent: Intent?):Boolean{
        var calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        var td = calendar.get(Calendar.DAY_OF_WEEK)
        when(td){
            Calendar.MONDAY -> {
                if(intent?.getBooleanExtra("Mon",false) == true) return true
            }
            Calendar.TUESDAY-> {
                if(intent?.getBooleanExtra("Tue",false) == true) return true
            }
            Calendar.WEDNESDAY -> {
                if(intent?.getBooleanExtra("Wed",false) == true) return true
            }
            Calendar.THURSDAY -> {
                if(intent?.getBooleanExtra("Thu",false) == true) return true
            }
            Calendar.FRIDAY -> {
                if(intent?.getBooleanExtra("Fri",false) == true) return true
            }
            Calendar.SATURDAY -> {
                if(intent?.getBooleanExtra("Sat",false) == true) return true
            }
            Calendar.SUNDAY -> {
                if(intent?.getBooleanExtra("Sun",false) == true) return true
            }
        }
        return false
    }


}