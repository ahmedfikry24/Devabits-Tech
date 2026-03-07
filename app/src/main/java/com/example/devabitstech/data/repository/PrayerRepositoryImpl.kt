package com.example.devabitstech.data.repository

import com.example.devabitstech.data.local.AppDatabase
import com.example.devabitstech.data.local.entity.toDomain
import com.example.devabitstech.data.local.entity.toRoomEntity
import com.example.devabitstech.data.remote.ApiService
import com.example.devabitstech.data.remote.dto.toEntityList
import com.example.devabitstech.data.remote.utils.NetworkConnectivityManager
import com.example.devabitstech.data.remote.utils.tryToCall
import com.example.devabitstech.domain.entity.Prayer
import com.example.devabitstech.domain.exceptions.NoCachedPrayersException
import com.example.devabitstech.domain.repository.PrayerRepository

class PrayerRepositoryImpl(
    private val apiService: ApiService,
    private val localStorage: AppDatabase,
    private val connectivityManager: NetworkConnectivityManager
) : PrayerRepository {
    override suspend fun getPrayerTimesByDate(date: String): List<Prayer> {
        val prayerDao = localStorage.prayerDao()
        val existingPrayers = prayerDao.getPrayersByDate(date)
        return if (!connectivityManager.checkCurrentConnectivity()) {
            existingPrayers.ifEmpty { throw NoCachedPrayersException() }.map { it.toDomain() }
        } else {
            existingPrayers.ifEmpty {
                val prayersInfoResponse =
                    tryToCall { apiService.getPrayerTimesByDate(date) }.toEntityList()
                val cachedPrayers = prayersInfoResponse.map { it.toRoomEntity() }

                localStorage.prayerDao().clearCache()
                localStorage.prayerDao().insertPrayers(cachedPrayers)

                cachedPrayers
            }.map { it.toDomain() }
        }
    }
}