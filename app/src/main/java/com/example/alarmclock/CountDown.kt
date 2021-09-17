package com.example.alarmclock

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
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
    private lateinit var countDown: CountDownTimer
    private var running: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountDownBinding.inflate(inflater,container,false)

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
        return binding.root
    }

    private fun hideMyKeyBoard(){
         if(view!= null){
             val hideMe = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
             hideMe.hideSoftInputFromWindow(view!!.windowToken,0)
         }
    }


    private fun startTime() {
        countDown = object :CountDownTimer(start,1000){
            override fun onTick(millisUntilFinished: Long) {
                start = millisUntilFinished
                updateCountDownText();
            }

            override fun onFinish() {
                start = 0
                running = false
                binding.btnResume.visibility = View.GONE
                binding.btnPlay.visibility = View.VISIBLE
                binding.timeCountdown.visibility = View.VISIBLE
                binding.btnStop.visibility = View.GONE
                binding.btnPause.visibility = View.GONE
                binding.timeLeft.visibility = View.GONE
                binding.timeCountdown.text.clear()
                Toast.makeText(context,"TIME UP!!!",Toast.LENGTH_LONG).show()
            }

        }.start()
        running = true
        binding.btnResume.visibility = View.GONE
        binding.btnPlay.visibility = View.GONE
        binding.timeCountdown.visibility = View.GONE
        binding.btnStop.visibility = View.VISIBLE
        binding.btnPause.visibility = View.VISIBLE
        binding.timeLeft.visibility = View.VISIBLE

    }

    private fun pauseTime() {
        running = false
        countDown.cancel()
        binding.btnPause.visibility = View.GONE
        binding.btnResume.visibility = View.VISIBLE
    }

    private fun stopTime(){
        running = false
        start = 0
        binding.btnResume.visibility = View.GONE
        binding.btnPlay.visibility = View.VISIBLE
        binding.timeCountdown.visibility = View.VISIBLE
        binding.btnStop.visibility = View.GONE
        binding.btnPause.visibility = View.GONE
        binding.timeLeft.visibility = View.GONE
        binding.timeCountdown.text.clear()
    }

    private fun updateCountDownText(){
        val minutes = ((start/1000)/60).toInt()
        val seconds = ((start/1000)%60).toInt()
        val timeFormat = String.format("%02d:%02d", minutes,seconds)
        binding.timeLeft.text = timeFormat
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong("millisLeft",start)
        outState.putBoolean("timerRunning", running)
    }


}