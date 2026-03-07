package com.example.devabitstech

import android.app.Application
import com.example.devabitstech.di.networkMode
import com.example.devabitstech.di.repositoryModule
import com.example.devabitstech.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DevabitsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DevabitsApplication)
            modules(networkMode, repositoryModule, useCaseModule)
        }
    }
}