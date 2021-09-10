package com.example.todo_app_hw.database

import androidx.room.TypeConverter
import java.util.*

class Converter {

    @TypeConverter
    fun dateTo(date: Date) : Long{
       return date.time
    }

    @TypeConverter
    fun longToDate(date: Long): Date {
       return Date(date)
    }
}