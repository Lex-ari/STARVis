package com.broncospace.android.starvis.spacecraft

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.Date

class SpacecraftTypeConverters {
    @TypeConverter
    fun fromDate(date: Date): Long {
        return date.time
    }

}