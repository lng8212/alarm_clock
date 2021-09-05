package com.example.alarmclock.recyclerview

import java.io.Serializable

data class Item(
    val time: String,
    val repeat: String,
    val turn: Boolean
): Serializable
