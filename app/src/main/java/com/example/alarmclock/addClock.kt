package com.example.alarmclock

import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.alarmclock.databinding.FragmentAddClockBinding
import kotlinx.android.synthetic.main.options.*
import kotlinx.android.synthetic.main.repeat.view.*


class addClock : Fragment() {
    private lateinit var binding: FragmentAddClockBinding
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var frequent: String
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
        frequent = "Một lần"
        binding.btnFrequently.setOnClickListener(){
            val mDialogView = LayoutInflater.from(context).inflate(R.layout.repeat,null)
            val mBuilder = AlertDialog.Builder(context).setView(mDialogView)
            val mAlertDialog = mBuilder.show()
            mAlertDialog.window?.setGravity(Gravity.BOTTOM)

            mDialogView.txt_motlan.setOnClickListener(){
                frequent = mDialogView.txt_motlan.text.toString()
                binding.frequently.text = frequent
                mAlertDialog.dismiss()


            }
            mDialogView.txt_hangngay.setOnClickListener(){
                frequent = mDialogView.txt_hangngay.text.toString()
                binding.frequently.text = frequent
                mAlertDialog.dismiss()

            }
            mDialogView.txt_t2t6.setOnClickListener(){
                frequent = mDialogView.txt_t2t6.text.toString()
                binding.frequently.text = frequent
                mAlertDialog.dismiss()

            }
            mDialogView.txt_tuychinh.setOnClickListener(){

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
                    if(AlertDialog.bt_t2.isChecked) date = date + "Thứ 2 "
                    if(AlertDialog.bt_t3.isChecked) date = date + "Thứ 3 "
                    if(AlertDialog.bt_t4.isChecked) date = date + "Thứ 4 "
                    if(AlertDialog.bt_t5.isChecked) date = date + "Thứ 5 "
                    if(AlertDialog.bt_t6.isChecked) date = date + "Thứ 6 "
                    if(AlertDialog.bt_t7.isChecked) date = date + "Thứ 7 "
                    if(AlertDialog.bt_t8.isChecked) date = date + "Chủ nhật "
                    Log.d("TAG", "value: " + date)
                    frequent = date
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
          hour = timePicker.hour.toString() + ":" + timePicker.minute.toString()
        }
        else hour = "00:00"
        val time = com.example.alarmclock.database.Time(0,hour,frequent,true)

        viewModel.insertTimes(time)
    }

}