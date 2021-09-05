package com.example.alarmclock

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.alarmclock.recyclerview.Item

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initItem()
        setContentView(R.layout.activity_main)
    }
    companion object{
        var data = mutableListOf<Item>()
    }
    private fun initItem(){
        data.add(Item("10:30","Một lần",true))
    }

}