package com.example.alarmclock.recyclerview

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.alarmclock.viewmodel.MainActivityViewModel
import com.example.alarmclock.R
import com.example.alarmclock.database.Time
import com.example.alarmclock.databinding.ItemBinding
import java.util.*


class ItemAdapter(var data: List<Time>, var viewModel: MainActivityViewModel) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    lateinit var context:Context
    lateinit var binding: ItemBinding

    inner class ViewHolder(val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val hour: TextView = binding.time
        val loop: TextView = binding.day
        val switch: Switch = binding.turnonOff


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(item: ViewHolder, position: Int) {
        item.hour.text = data[position].hour
        item.loop.text = data[position].repeat
        item.switch.isChecked = data[position].turn!!

        item.itemView.setOnClickListener() {
            var bundle = Bundle()
            bundle.putSerializable("now", data[position])
            item.itemView.findNavController()
                .navigate(R.id.action_mainFragment_to_fragment_add_clock, bundle) // gửi data[position] đến fragment addClock
        }
        item.itemView.setOnLongClickListener {
            viewModel.deleteTimes(data[position])
            data[position].cancelAlarm(context)
            return@setOnLongClickListener true
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}