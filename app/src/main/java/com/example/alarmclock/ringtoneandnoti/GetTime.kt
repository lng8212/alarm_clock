package com.example.alarmclock.ringtoneandnoti

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.SystemClock
import android.util.Log
import android.widget.Toast
import java.util.*

class GetTime constructor(val context: Context) {
////    var context: Context? = null
//    constructor(val context: Context){
////        this.context = context
//    }
    fun setAlarm(time:String){
        val hour = (time[0].toString() + time[1].toString()).toInt()
        val minute = (time[3].toString() + time[4].toString()).toInt()
        val cal = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY,hour)
            set(Calendar.MINUTE,minute)
            set(Calendar.SECOND,0)
        }

        val am = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        var intent = Intent(context,AlarmReceiver::class.java).let { intent ->
            intent.putExtra("Messenger", time)
            PendingIntent.getBroadcast(context, 0, intent, 0)
        }
        am.set(AlarmManager.RTC_WAKEUP,cal.timeInMillis,intent)
//        am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
//            SystemClock.elapsedRealtime() + 60 * 1000,intent)
    }
}