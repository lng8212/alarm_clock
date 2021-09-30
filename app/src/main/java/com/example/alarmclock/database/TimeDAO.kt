package com.example.alarmclock.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TimeDAO {
    @Query("SELECT * FROM Time ORDER BY id ASC")
    fun getAll(): LiveData<List<Time>>

    @Query("SELECT * FROM time WHERE hour LIKE :hour LIMIT 1")
    suspend fun findByHour(hour: String): Time  //suspend fun dùng để chạy coroutine (khi chạy nó có thể dừng tạm thời, để hàm khác chạy sau nó resume

    @Insert
    suspend fun insert(time: Time)

    @Delete
    suspend fun delete(time: Time)

    @Update
    suspend fun update(time: Time)

    @Query("DELETE FROM time")
    suspend fun deleteAllAlarm()
}