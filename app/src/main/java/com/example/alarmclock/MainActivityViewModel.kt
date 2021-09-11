package com.example.alarmclock

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.alarmclock.database.Time
import com.example.alarmclock.database.TimeDatabase

class MainActivityViewModel(app: Application): AndroidViewModel(app) {
    lateinit var  data: MutableLiveData<List<Time>>

    init {
        data = MutableLiveData()
    }

    fun getAllTimesObservers():MutableLiveData<List<Time>>{
        getAllTimes()
        return data

    }
    fun getAllTimes(){
        val timeDao = TimeDatabase.getTimeDatabase((getApplication()))?.timeDao()
        val list = timeDao?.getAll()
        data.postValue(list)
    }
    fun insertTimes(entity: Time){
        val timeDao = TimeDatabase.getTimeDatabase((getApplication()))?.timeDao()
        timeDao?.insert(entity)
        getAllTimes()
    }
    fun updateTimes(entity: Time){
        val timeDao = TimeDatabase.getTimeDatabase((getApplication()))?.timeDao()
        timeDao?.update(entity)
        getAllTimes()
    }
    fun deleteTimes(entity: Time){
        val timeDao = TimeDatabase.getTimeDatabase((getApplication()))?.timeDao()
        timeDao?.delete(entity)
        getAllTimes()
    }
}