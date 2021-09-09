package com.example.alarmclock.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.security.AccessControlContext

@Database(entities = arrayOf(Time::class), version = 1)
abstract class TimeDatabase: RoomDatabase() {
    abstract fun timeDao():TimeDAO
    companion object{
        private var INSTANCE: TimeDatabase? = null
        fun getTimeDatabase(context: Context): TimeDatabase?{
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext, TimeDatabase::class.java, "TimeDB")
                    .allowMainThreadQueries()
                    .build()

            }
            return INSTANCE
        }
    }
}