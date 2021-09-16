package com.example.alarmclock.database

import androidx.lifecycle.LiveData

class AlarmRepository(private var timeDao: TimeDAO) {
    var  data: LiveData<List<Time>> = timeDao.getAll()

    suspend fun insertTimes(entity: Time){
        timeDao.insert(entity)
    }
    suspend fun updateTimes(entity: Time){
        timeDao.update(entity)

    }
    suspend fun deleteTimes(entity: Time){
        timeDao.delete(entity)
    }
}