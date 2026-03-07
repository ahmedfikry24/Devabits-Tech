package com.example.devabitstech.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.devabitstech.data.local.entity.PrayerEntity

@Dao
interface PrayerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPrayers(prayers: List<PrayerEntity>)

    @Query("SELECT * FROM PrayerEntity WHERE date_gregorian = :date")
    suspend fun getPrayersByDate(date: String): List<PrayerEntity>

    @Query("DELETE FROM PrayerEntity")
    suspend fun clearCache()
}