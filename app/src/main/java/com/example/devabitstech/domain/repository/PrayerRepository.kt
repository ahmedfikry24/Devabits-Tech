package com.example.devabitstech.domain.repository

import com.example.devabitstech.domain.entity.Prayer

interface PrayerRepository {
    suspend fun getPrayerTimesByDate(date: String): List<Prayer>
}