package com.example.alarmclock.database

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.alarmclock.ringtoneandnoti.AlarmReceiver
import kotlinx.coroutines.flow.DEFAULT_CONCURRENCY
import java.io.Serializable
import java.util.*

@Entity
data class Time(

    @ColumnInfo(name = "hour")
    var hour: String,
    @ColumnInfo(name = "repeat")
    var repeat: String,
    @ColumnInfo(name = "turn")
    var turn: Boolean,
    var once: Boolean,
    var Mon: Boolean,
    var Tue: Boolean,
    var Wed: Boolean,
    var Thu: Boolean,
    var Fri: Boolean,
    var Sat: Boolean,
    var Sun: Boolean,
    @PrimaryKey(autoGenerate = true)
    var id: Int
) : Serializable {


    fun schedule(context: Context) {
        val hour = (this.hour[0].toString() + this.hour[1].toString()).toInt()
        val minute = (this.hour[3].toString() + this.hour[4].toString()).toInt()
        var calendar = Calendar.getInstance().apply { // set time cho calendar
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
        }
        Log.e("id schedule", id.toString())
        Log.e("hour", this.hour[0].toString() + this.hour[1].toString())
        Log.e("minute", this.hour[3].toString() + this.hour[4].toString())

        var intent = Intent(context, AlarmReceiver::class.java) //put data qua Receiver
        intent.putExtra("handleAlarm", "on")
        intent.putExtra("Repeat", this.once)
        intent.putExtra("Mon", this.Mon)
        intent.putExtra("Tue", this.Tue)
        intent.putExtra("Wed", this.Wed)
        intent.putExtra("Thu", this.Thu)
        intent.putExtra("Fri", this.Fri)
        intent.putExtra("Sat", this.Sat)
        intent.putExtra("Sun", this.Sun)
        var pendingIntent =
            PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT)// pending intent ( cho ph??p nh??ng application ??? ngo??i c?? quy???n th???c thi 1 ??o???n m?? cho tr?????c)
        val alarmManager =
            context.getSystemService(Context.ALARM_SERVICE) as AlarmManager //g???i Service Alarm
        Log.e("Once", this.once.toString())
        if (this.once == false) {
            Log.e("v??o ????y", "111")
            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP, //????nh th???c thi???t b??? click ho???t pending intent v??o th???i gian ch??? ?????nh
                calendar.timeInMillis,
                AlarmManager.INTERVAL_DAY,// l???p l???i h??ng ng??y
                pendingIntent
            )
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
        }
    }


    fun cancelAlarm(context: Context) {
        var intent = Intent(context, AlarmReceiver::class.java)
        var pendingIntent =
            PendingIntent.getBroadcast(context, this.id, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val alarmManager =
            context.applicationContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(pendingIntent) //hu??? alarm
    }
}
