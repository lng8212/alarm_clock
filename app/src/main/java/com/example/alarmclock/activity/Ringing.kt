package com.example.alarmclock.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
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
            var intentService: Intent = Intent(applicationContext, AlarmReceiver::class.java)
            intentService.putExtra("handleAlarm","off")
            applicationContext.sendBroadcast(intentService)
            finish() // kết thúc activity
        }
        btnDelay.setOnClickListener() {
            var calendar = Calendar.getInstance()
            var intentService: Intent = Intent(applicationContext, AlarmReceiver::class.java)
            intentService.putExtra("handleAlarm","off")
            applicationContext.sendBroadcast(intentService)
            var hour = calendar.get(Calendar.HOUR_OF_DAY)
            var minute = calendar.get(Calendar.MINUTE)
            if (minute < 55) minute += 5
            else {
                minute -= 55
                hour += 1
            }
            val hourf = (if (hour >= 10) hour.toString()
            else ("0" +hour.toString())) + ":" + (if (minute >= 10) minute.toString()
            else ("0" +minute.toString()))
            var newAlarm = com.example.alarmclock.database.Time(
                hourf,
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
                System.currentTimeMillis().toInt()
            )
            newAlarm.schedule(applicationContext) // tạo clock mới và lên lịch cho nó rung sau 5p
            finish()
        }
    }

}