package com.example.alarmclock.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Time(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    @ColumnInfo(name = "hour")
    val hour: String,
    @ColumnInfo(name = "repeat")
    val repeat: String,
    @ColumnInfo(name = "turn")
    val turn: Boolean
) : Serializable
