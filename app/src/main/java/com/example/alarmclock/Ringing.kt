package com.example.alarmclock

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.alarmclock.database.TimeDatabase
import com.example.alarmclock.databinding.ActivityRingingBinding
import com.example.alarmclock.ringtoneandnoti.AlarmReceiver
import java.util.*

class Ringing:AppCompatActivity() {
    private lateinit var binding : ActivityRingingBinding
    private lateinit var btnOff: Button
    private lateinit var btnDelay: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRingingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        btnOff = binding.btnOff
        btnDelay = binding.btnDelay
        btnOff.setOnClickListener(){
            var intentService  = Intent(applicationContext,AlarmReceiver::class.java)
            intentService.putExtra("handleAlarm","off")
            applicationContext.sendBroadcast(intentService)
            finish()
        }
        btnDelay.setOnClickListener(){
            var calendar = Calendar.getInstance()
            var intentService = Intent(applicationContext, AlarmReceiver::class.java)
            intentService.putExtra("handleAlarm","off")
            var newAlarm = com.example.alarmclock.database.Time(calendar.get(Calendar.HOUR_OF_DAY).toString() + ":" + calendar.get(Calendar.MINUTE),"",false,false,false,false, false, false, false, false, false,TimeDatabase.stt++)
            newAlarm.schedule(this)
            applicationContext.sendBroadcast(intentService)
            finish()
        }
    }

}