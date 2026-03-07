package com.example.devabitstech.di

import com.example.devabitstech.data.utils.PrayerAlarmScheduler
import com.example.devabitstech.data.utils.PrayerNotificationService
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val alarmModule = module {
    single { PrayerAlarmScheduler(androidContext()) }
    single { PrayerNotificationService(androidContext()) }
}