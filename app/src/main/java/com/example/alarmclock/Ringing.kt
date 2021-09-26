package com.example.alarmclock

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.alarmclock.database.TimeDatabase
import com.example.alarmclock.databinding.ActivityRingingBinding
import com.example.alarmclock.ringtoneandnoti.AlarmReceiver
import java.util.*

class Ringing : AppCompatActivity() {
    private lateinit var binding: ActivityRingingBinding
    private lateinit var btnOff: ImageButton
    private lateinit var btnDelay: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRingingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        btnOff = binding.btnOff
        btnDelay = binding.btnDelay
        btnOff.setOnClickListener() {
            var intentService = Intent(applicationContext, AlarmReceiver::class.java)
            intentService.putExtra("handleAlarm", "off")
            applicationContext.sendBroadcast(intentService)
            finish()
        }
        btnDelay.setOnClickListener() {
            var calendar = Calendar.getInstance()
            var intentService = Intent(applicationContext, AlarmReceiver::class.java)
            intentService.putExtra("handleAlarm", "off")
            var hour = calendar.get(Calendar.HOUR_OF_DAY)
            var minute = calendar.get(Calendar.MINUTE)
            if (minute < 55) minute += 5
            else {
                minute -= 55
                hour += 1
            }

            var newAlarm = com.example.alarmclock.database.Time(
                hour.toString() + ":" + minute.toString(),
                "",
                false,
                true,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                TimeDatabase.stt++
            )
            newAlarm.schedule(this)
            applicationContext.sendBroadcast(intentService)
            finish()
        }
    }

}