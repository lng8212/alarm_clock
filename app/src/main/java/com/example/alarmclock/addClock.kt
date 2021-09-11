package com.example.alarmclock

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.alarmclock.databinding.FragmentAddClockBinding


class addClock : Fragment() {
    private lateinit var binding: FragmentAddClockBinding
    private lateinit var viewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddClockBinding.inflate(inflater,container,false)
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCancel.setOnClickListener(){
            Navigation.findNavController(binding.root).navigate(R.id.action_fragment_add_clock_to_mainFragment)
        }
        binding.btnDone.setOnClickListener(){
            getTime()
            Navigation.findNavController(binding.root).navigate(R.id.action_fragment_add_clock_to_mainFragment)
        }
    }
    private fun getTime(){
        var timePicker = binding.timepicker
        var hour : String
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
          hour = timePicker.hour.toString() + ":" + timePicker.minute.toString()
        }
        else hour = "00:00"
        val time = com.example.alarmclock.database.Time(0,hour,"Một lần",true)
        viewModel.insertTimes(time)
    }

}