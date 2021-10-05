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
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.alarmclock.database.Time
import com.example.alarmclock.database.TimeDatabase
import com.example.alarmclock.databinding.FragmentAddClockBinding
import kotlinx.android.synthetic.main.fragment_add_clock.*
import kotlinx.android.synthetic.main.options.*
import kotlinx.android.synthetic.main.repeat.view.*


class addClock : Fragment() {
    private lateinit var binding: FragmentAddClockBinding
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var frequent: String
    private lateinit var updateAlarm: Time
    private var Mon = false
    private var Tue = false
    private var Wed = false
    private var Thu = false
    private var Fri = false
    private var Sat = false
    private var Sun = false
    private var once = true
    private var check = true
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddClockBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java) //trả về viewModel đã tồn tại từ lớp MainActivityViewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var nowAlarm = arguments?.getSerializable("now") // trả về clock ở nếu sửa ( click vào )
        if (nowAlarm != null) {
            check = false
            updateAlarm = nowAlarm as Time


        }
        binding.btnCancel.setOnClickListener() {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_fragment_add_clock_to_mainFragment) // trả về trang chính nếu k tạo mới
        }
        binding.btnDone.setOnClickListener() {
            getTime()
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_fragment_add_clock_to_mainFragment) // trả về trang chính (tạo mới clock)
        }
        frequent = "Một lần"
        binding.btnFrequently.setOnClickListener() {
            val mDialogView = LayoutInflater.from(context).inflate(R.layout.repeat, null)  // tạo view với layout repeat
            val mBuilder = AlertDialog.Builder(context).setView(mDialogView)      //tạo AlertDialog với view là layout repeat
            val mAlertDialog = mBuilder.show()              // show lên màn hình
            mAlertDialog.window?.setGravity(Gravity.BOTTOM) // set gravity ở dưới

            mDialogView.txt_motlan.setOnClickListener() {
                once = true
                frequent = mDialogView.txt_motlan.text.toString()
                binding.frequently.text = frequent
                mAlertDialog.dismiss() // huỷ AlertDialog


            }
            mDialogView.txt_hangngay.setOnClickListener() {
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
            mDialogView.txt_t2t6.setOnClickListener() {
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
            mDialogView.txt_tuychinh.setOnClickListener() {
                once = false
                val DialogView = LayoutInflater.from(context).inflate(R.layout.options, null) // tạo view với layout options
                val Builder = AlertDialog.Builder(context).setView(DialogView)
                mAlertDialog.dismiss()
                val AlertDialog = Builder.show()
                AlertDialog.window?.setGravity(Gravity.BOTTOM)
                AlertDialog.btn_huy.setOnClickListener() {
                    AlertDialog.dismiss()
                }

                AlertDialog.btn_xong.setOnClickListener() {
                    var date: String = ""
                    if (AlertDialog.bt_t2.isChecked) {
                        date = date + "T2 "
                        Mon = true
                    }
                    if (AlertDialog.bt_t3.isChecked) {
                        date = date + "T3 "
                        Tue = true
                    }
                    if (AlertDialog.bt_t4.isChecked) {
                        date = date + "T4 "
                        Wed = true
                    }
                    if (AlertDialog.bt_t5.isChecked) {
                        date = date + "T5 "
                        Thu = true
                    }
                    if (AlertDialog.bt_t6.isChecked) {
                        date = date + "T6 "
                        Fri = true
                    }
                    if (AlertDialog.bt_t7.isChecked) {
                        date = date + "T7 "
                        Sat = true
                    }
                    if (AlertDialog.bt_t8.isChecked) {
                        date = date + "CN "
                        Sun = true
                    }
                    Log.d("TAG", "value: " + date)
                    frequent = date.trim() // loại bỏ khoảng trắng thừa 2 đầu
                    binding.frequently.text = frequent
                    AlertDialog.dismiss()
                }

            }
        }
    }

    private fun getTime() {
        var timePicker = binding.timepicker
        var hour: String
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            hour = (if (timePicker.hour >= 10) timePicker.hour.toString()
            else ("0" + timePicker.hour.toString())) + ":" + (if (timePicker.minute >= 10) timePicker.minute.toString()
            else ("0" + timePicker.minute.toString()))        // chuẩn hoá về dạng hh:mm
        } else hour = "00:00"
        val time: com.example.alarmclock.database.Time
        if(check == true){
            time = com.example.alarmclock.database.Time(
                hour.trim(),
                frequent,
                true,
                once,
                Mon,
                Tue,
                Wed,
                Thu,
                Fri,
                Sat,
                Sun,
                System.currentTimeMillis().toInt()
            )
            viewModel.insertTimes(time) // thêm vào database
            this.context?.let { time.schedule(it) } // lên lịch báo thức
        }
        else{
            time = com.example.alarmclock.database.Time(
                hour.trim(),
                frequent,
                true,
                once,
                Mon,
                Tue,
                Wed,
                Thu,
                Fri,
                Sat,
                Sun,
               updateAlarm.id
            )
            viewModel.updateTimes(time) // thêm vào database
            updateAlarm.cancelAlarm(context!!) // huỷ hẹn giờ báo thức
            this.context?.let { time.schedule(it) } // lên lịch báo thức
        }


    }

}