package com.example.devabitstech.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.devabitstech.data.local.dao.PrayerDao
import com.example.devabitstech.data.local.entity.PrayerEntity

@Database(
    entities = [PrayerEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun prayerDao(): PrayerDao
}