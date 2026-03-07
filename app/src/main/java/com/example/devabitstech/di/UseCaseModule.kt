package com.example.devabitstech.di

import com.example.devabitstech.domain.usecase.GetPrayerTimeByDateUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetPrayerTimeByDateUseCase(get()) }
}