package com.example.alarmclock.ringtoneandnoti

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import java.util.*

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) { // khi nhận đc pendingintent
        Log.e("Receiver", "Came")
        var handleAlarm : String? = intent?.extras?.getString("handleAlarm")
        var myIntent = Intent(context,AlarmService::class.java)
        Log.e("handleAlarm",handleAlarm.toString())
        myIntent.putExtra("handleAlarm1",handleAlarm)
        if (intent?.getBooleanExtra("Repeat", true) == true) {// nếu báo thức là 1 lần
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { // SDK >  android 0 thì startFore
                context?.startForegroundService(myIntent)

            } else {
                context?.startService(myIntent)
            }
        } else {
            if (today(intent)) { // nếu không thì kiểm tra hôm nay có trùng với ngày báo thức không
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    context?.startForegroundService(myIntent)
                } else {
                    context?.startService(myIntent)
                }
            }
        }

    }

    private fun today(intent: Intent?): Boolean {
        var calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        var td = calendar.get(Calendar.DAY_OF_WEEK)
        when (td) {
            Calendar.MONDAY -> {
                if (intent?.getBooleanExtra("Mon", false) == true) return true
            }
            Calendar.TUESDAY -> {
                if (intent?.getBooleanExtra("Tue", false) == true) return true
            }
            Calendar.WEDNESDAY -> {
                if (intent?.getBooleanExtra("Wed", false) == true) return true
            }
            Calendar.THURSDAY -> {
                if (intent?.getBooleanExtra("Thu", false) == true) return true
            }
            Calendar.FRIDAY -> {
                if (intent?.getBooleanExtra("Fri", false) == true) return true
            }
            Calendar.SATURDAY -> {
                if (intent?.getBooleanExtra("Sat", false) == true) return true
            }
            Calendar.SUNDAY -> {
                if (intent?.getBooleanExtra("Sun", false) == true) return true
            }
        }
        return false
    }


}