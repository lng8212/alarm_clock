package com.example.alarmclock

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SharedMemory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.alarmclock.databinding.FragmentCountDownBinding
import com.example.alarmclock.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.item.*
import java.lang.Exception
import androidx.core.content.ContextCompat.getSystemService





class CountDown : Fragment() {
    private lateinit var binding: FragmentCountDownBinding
    private var start: Long = 0
    private var countDown: CountDownTimer? = null
    private var running: Boolean = true
    private var isStopped: Boolean = false
    private var endTime: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountDownBinding.inflate(inflater,container,false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBaothuc2.setOnClickListener(){
            Navigation.findNavController(binding.root).navigate(R.id.action_countDown_to_mainFragment)
        }
        binding.btnPlay.setOnClickListener(){
            val a = binding.timeCountdown.text.toString()
            try {
                start = (((a[0].toString() + a[1].toString()).toLong())*60 + ((a[3].toString() + a[4].toString()).toLong()))*1000
                hideMyKeyBoard()
                startTime()
            }
            catch (e: Exception){
                Toast.makeText(context,"Định dạng nhập là mm:ss", Toast.LENGTH_LONG).show()
            }

        }
        binding.btnPause.setOnClickListener(){
            pauseTime()
        }
        binding.btnResume.setOnClickListener(){
            startTime()
        }
        binding.btnStop.setOnClickListener(){
            stopTime()
        }
    }
    private fun hideMyKeyBoard(){
         if(view!= null){
             val hideMe = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
             hideMe.hideSoftInputFromWindow(view!!.windowToken,0)
         }
    }


    private fun startTime() {
        endTime = System.currentTimeMillis() + start
        countDown = object :CountDownTimer(start,1000){
            override fun onTick(millisUntilFinished: Long) {
                start = millisUntilFinished
                updateCountDownText();
            }

            override fun onFinish() {
                isStopped = true
                running = false
                updateButtons()
            }


        }.start()
        running = true
        updateButtons()

    }

    private fun pauseTime() {
        running = false
        updateButtons()
    }

    private fun stopTime(){
        try{
            isStopped = true
            running = false
            updateButtons()
            countDown?.cancel()
        }
        catch (e: Exception){

        }
    }

    private fun updateButtons() {
        if (running) {
            binding.btnResume.visibility = View.GONE
            binding.btnPlay.visibility = View.GONE
            binding.timeCountdown.visibility = View.GONE
            binding.btnStop.visibility = View.VISIBLE
            binding.btnPause.visibility = View.VISIBLE
            binding.timeLeft.visibility = View.VISIBLE
        } else {
            if (isStopped || start < 1000) {
                isStopped = false
                binding.btnResume.visibility = View.GONE
                binding.btnPlay.visibility = View.VISIBLE
                binding.timeCountdown.visibility = View.VISIBLE
                binding.btnStop.visibility = View.GONE
                binding.btnPause.visibility = View.GONE
                binding.timeLeft.visibility = View.GONE
                binding.timeCountdown.text.clear()
            } else {
                countDown?.cancel()
                binding.btnPause.visibility = View.GONE
                binding.btnResume.visibility = View.VISIBLE
            }
        }
    }

    private fun updateCountDownText(){
        val minutes = ((start/1000)/60).toInt()
        val seconds = ((start/1000)%60).toInt()
        val timeFormat = String.format("%02d:%02d", minutes,seconds)
        binding.timeLeft.text = timeFormat
    }

    override fun onStop() {
        super.onStop()
        val prefs = context!!.getSharedPreferences("prefs", MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putLong("millisLeft",start)
        editor.putBoolean("timerRunning",running)
        editor.putLong("endTime",endTime)
        editor.apply()
    }
    override fun onStart() {
        super.onStart()
        val prefs = context!!.getSharedPreferences("prefs", MODE_PRIVATE)
        start =  prefs.getLong("millisLeft",0)
        running = prefs.getBoolean("timerRunning",false)
        if (running){
            endTime = prefs.getLong("endTime",0)
            start = endTime - System.currentTimeMillis()
            if(start<0){
                start = 0
                running = false
                updateCountDownText()
                updateButtons()
            }
            else {
                startTime()
            }
        }

    }

}