package com.example.alarmclock

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.alarmclock.database.AlarmRepository
import com.example.alarmclock.database.Time
import com.example.alarmclock.database.TimeDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(app: Application) : AndroidViewModel(app) {

    var alarmRepository: AlarmRepository
    var data: LiveData<List<Time>> = MutableLiveData()

    init {
        val timeDao = TimeDatabase.getTimeDatabase(app).timeDao()
        alarmRepository = AlarmRepository(timeDao)
        data = alarmRepository.data
    }

    fun insertTimes(entity: Time) {
        viewModelScope.launch(Dispatchers.IO) { //dispatchers.IO để tối ưu việc chạy ngoài luồng chính
            alarmRepository.insertTimes(entity)
        }
    }

    fun updateTimes(entity: Time) {
        viewModelScope.launch(Dispatchers.IO) {//viewmodelscope tự động huỷ bất kỉ coroutine nào mà khỏi động bởi viewmodel mà khi viewmodel bị huỷ
            alarmRepository.updateTimes(entity)
        }

    }

    fun deleteTimes(entity: Time) {
        viewModelScope.launch(Dispatchers.IO) {
            alarmRepository.deleteTimes(entity)
        }
    }


}