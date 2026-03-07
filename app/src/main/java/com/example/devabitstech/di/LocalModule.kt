package com.example.devabitstech.di


import androidx.room.Room
import com.example.devabitstech.data.local.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localModule = module {
    single<AppDatabase> {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "DevabitsTechDB"
        ).build()
    }
}