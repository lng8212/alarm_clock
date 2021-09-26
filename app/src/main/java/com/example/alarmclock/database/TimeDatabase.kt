package com.example.alarmclock.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = arrayOf(Time::class),
    version = 1

)
abstract class TimeDatabase : RoomDatabase() {
    abstract fun timeDao(): TimeDAO

    companion object {
        var stt = 0
        private var INSTANCE: TimeDatabase? = null
        fun getTimeDatabase(context: Context): TimeDatabase {
            var tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this)
            {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TimeDatabase::class.java,
                    "alarm_database2"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}