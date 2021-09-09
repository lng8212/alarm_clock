package com.example.alarmclock

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        initItem()
        setContentView(R.layout.activity_main)
    }
//    companion object{
//        var data = mutableListOf<Item>()
//    }
//    private fun initItem(){
//        data.add(Item("10:30","Một lần",true))
//    }

}