package com.example.alarmclock

import android.app.AlarmManager
import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.alarmclock.database.Time
import com.example.alarmclock.database.TimeDatabase
import com.example.alarmclock.databinding.FragmentAddClockBinding
import kotlinx.android.synthetic.main.options.*
import kotlinx.android.synthetic.main.repeat.view.*


class addClock : Fragment() {
    private lateinit var binding: FragmentAddClockBinding
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var frequent: String
    private var check = false
    private lateinit var updateAlarm : Time
    private lateinit var alarmManager: AlarmManager
    private var Mon = false
    private var Tue = false
    private var Wed = false
    private var Thu = false
    private var Fri = false
    private var Sat = false
    private var Sun = false
    private var once = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddClockBinding.inflate(inflater,container,false)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        var nowAlarm = arguments?.getSerializable("now")
            if (nowAlarm != null) {
            check = true
            updateAlarm = nowAlarm as Time
            nowAlarm.cancelAlarm(view.context)

        }
        else {
        }
        binding.btnCancel.setOnClickListener(){
            check = false
            Navigation.findNavController(binding.root).navigate(R.id.action_fragment_add_clock_to_mainFragment)
        }
        binding.btnDone.setOnClickListener(){
            getTime()
            check = false
            Navigation.findNavController(binding.root).navigate(R.id.action_fragment_add_clock_to_mainFragment)
        }
        frequent = "Một lần"
        binding.btnFrequently.setOnClickListener(){
            val mDialogView = LayoutInflater.from(context).inflate(R.layout.repeat,null)
            val mBuilder = AlertDialog.Builder(context).setView(mDialogView)
            val mAlertDialog = mBuilder.show()
            mAlertDialog.window?.setGravity(Gravity.BOTTOM)

            mDialogView.txt_motlan.setOnClickListener(){
                once = true
                frequent = mDialogView.txt_motlan.text.toString()
                binding.frequently.text = frequent
                mAlertDialog.dismiss()


            }
            mDialogView.txt_hangngay.setOnClickListener(){
                once = false
                frequent = mDialogView.txt_hangngay.text.toString()
                binding.frequently.text = frequent
                Mon = true
                Tue = true
                Wed = true
                Thu = true
                Fri = true
                Sat = true
                Sun = true
                mAlertDialog.dismiss()

            }
            mDialogView.txt_t2t6.setOnClickListener(){
                once = false
                frequent = mDialogView.txt_t2t6.text.toString()
                binding.frequently.text = frequent
                Mon = true
                Tue = true
                Wed = true
                Thu = true
                Fri = true
                mAlertDialog.dismiss()

            }
            mDialogView.txt_tuychinh.setOnClickListener(){
                once = false
                val DialogView = LayoutInflater.from(context).inflate(R.layout.options,null)
                val Builder = AlertDialog.Builder(context).setView(DialogView)
                mAlertDialog.dismiss()
                val AlertDialog = Builder.show()
                AlertDialog.window?.setGravity(Gravity.BOTTOM)
                AlertDialog.btn_huy.setOnClickListener(){
                    AlertDialog.dismiss()
                }

                AlertDialog.btn_xong.setOnClickListener(){
                    var date : String = ""
                    if(AlertDialog.bt_t2.isChecked) {
                        date = date + "T2 "
                        Mon = true
                    }
                    if(AlertDialog.bt_t3.isChecked){
                        date = date + "T3 "
                        Tue = true
                    }
                    if(AlertDialog.bt_t4.isChecked) {
                        date = date + "T4 "
                        Wed = true
                    }
                    if(AlertDialog.bt_t5.isChecked) {
                        date = date + "T5 "
                        Thu = true
                    }
                    if(AlertDialog.bt_t6.isChecked) {
                        date = date + "T6 "
                        Fri = true
                    }
                    if(AlertDialog.bt_t7.isChecked) {
                        date = date + "T7 "
                        Sat = true
                    }
                    if(AlertDialog.bt_t8.isChecked) {
                        date = date + "CN "
                        Sun = true
                    }
                    Log.d("TAG", "value: " + date)
                    frequent = date.trim()
                    binding.frequently.text = frequent
                    AlertDialog.dismiss()
                }

            }
        }
    }
    private fun getTime(){
        var timePicker = binding.timepicker
        var hour : String
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            hour = (if(timePicker.hour>=10) timePicker.hour.toString()
            else ("0"+timePicker.hour.toString())) + ":" + (if(timePicker.minute>=10) timePicker.minute.toString()
            else ("0"+timePicker.minute.toString()))
        }
        else hour = "00:00"
        val time = com.example.alarmclock.database.Time(hour.trim(),frequent,true,once,Mon,Tue, Wed, Thu, Fri, Sat, Sun,TimeDatabase.stt++)
        if(check == true) {
            viewModel.deleteTimes(updateAlarm)
        }
        viewModel.insertTimes(time)
        this.context?.let { time.schedule(it) }

    }

}