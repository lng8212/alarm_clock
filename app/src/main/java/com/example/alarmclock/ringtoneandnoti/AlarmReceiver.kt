package com.example.alarmclock.ringtoneandnoti

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class AlarmReceiver : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context,"Alarm Ringing",Toast.LENGTH_LONG).show()
        val message = intent?.getStringExtra("Messenger")
        Log.d("check","mess $message")
//        if(intent!!.action.equals("com.tester.alarmmanager")){
//            var b= intent.extras
//            Toast.makeText(context,b!!.getString("Messenger"), Toast.LENGTH_LONG).show()
//
//        }
    }

}