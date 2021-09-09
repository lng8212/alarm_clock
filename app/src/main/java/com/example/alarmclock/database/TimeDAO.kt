package com.example.alarmclock.database

import androidx.room.*

@Dao
interface TimeDAO {
    @Query("SELECT * FROM time")
    fun getAll(): List<Time>
    @Query("SELECT * FROM time WHERE hour LIKE :hour LIMIT 1")
    fun findByHour(hour: String): Time
    @Insert
    fun insert(times: Time?)
    @Delete
    fun delete (time: Time?)
    @Update
    fun update(time: Time?)
}