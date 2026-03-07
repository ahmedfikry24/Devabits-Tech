package com.example.devabitstech.data.remote

import com.example.devabitstech.data.remote.dto.PrayerTimesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("timingsByCity/{date}")
    suspend fun getPrayerTimesByDate(
        @Path("date") date: String,
        @Query("city") city: String,
        @Query("country") country: String,
        @Query("method") method: Int
    ): Response<PrayerTimesDto>
}