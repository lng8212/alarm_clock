package com.example.alarmclock.recyclerview

import android.app.AlertDialog
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Switch
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.alarmclock.MainActivityViewModel
import com.example.alarmclock.R
import com.example.alarmclock.database.Time
import com.example.alarmclock.databinding.FragmentMainBinding
import com.example.alarmclock.databinding.ItemBinding
import com.example.alarmclock.mainFragment
import java.util.*


class ItemAdapter(var data: List<Time>, var viewModel: MainActivityViewModel) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    lateinit var binding: ItemBinding

    inner class ViewHolder(val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val hour: TextView = binding.time
        val loop: TextView = binding.day
        val switch: Switch = binding.turnonOff


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
            return@setOnLongClickListener true
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}