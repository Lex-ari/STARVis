package com.broncospace.android.starvis.spacecraft

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ SpacecraftItem::class ], version=1)
@TypeConverters(SpacecraftTypeConverters::class)
abstract class SpacecraftDatabase : RoomDatabase() {
    abstract fun spacecraftDao() : SpacecraftDao
}