package com.example.alarmclock.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.alarmclock.MainActivityViewModel
import com.example.alarmclock.R
import com.example.alarmclock.database.Time
import com.example.alarmclock.databinding.ItemBinding
import java.util.*


class ItemAdapter(var data :List<Time>, var viewModel: MainActivityViewModel): RecyclerView.Adapter<ItemAdapter.ViewHolder>()  {

    fun setListData(data: ArrayList<Time>){
        this.data = data
    }
    inner class ViewHolder(val binding: ItemBinding):
        RecyclerView.ViewHolder(binding.root){
        val hour: TextView = binding.time
        val loop: TextView = binding.day
        val switch: Switch = binding.turnonOff


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = ItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(item: ViewHolder, position: Int) {
        item.hour.text = data[position].hour
        item.loop.text = data[position].repeat
        item.switch.isChecked = data[position].turn!!
        item.itemView.setOnClickListener(){
            var bundle = Bundle()
            bundle.putSerializable("now", data[position])
            Navigation.findNavController(item.itemView).navigate(R.id.action_mainFragment_to_fragment_add_clock)
        }



    }

    override fun getItemCount(): Int {
        return data.size
    }
}