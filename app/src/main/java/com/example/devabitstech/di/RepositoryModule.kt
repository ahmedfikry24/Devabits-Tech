package com.example.devabitstech.di

import com.example.devabitstech.data.remote.ApiService
import com.example.devabitstech.data.repository.PrayerRepositoryImpl
import com.example.devabitstech.domain.repository.PrayerRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<PrayerRepository> { PrayerRepositoryImpl(get<ApiService>()) }
}