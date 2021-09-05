package com.example.alarmclock.recyclerview

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SwitchCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import com.example.alarmclock.databinding.ItemBinding


class ItemAdapter(val data: MutableList<Item>): RecyclerView.Adapter<ItemAdapter.ViewHolder>()  {
    inner class ViewHolder(val binding: ItemBinding):RecyclerView.ViewHolder(binding.root){
        val hour: TextView = binding.time
        val loop: TextView = binding.day
        val switch: Switch = binding.turnonOff

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = ItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(item: ViewHolder, position: Int) {
        item.hour.text = data.get(position).time
        item.loop.text = data.get(position).repeat
        item.switch.isChecked = data.get(position).turn
        Log.d("Process","Đến dây")
    }

    override fun getItemCount(): Int {
        return data.size
    }
}