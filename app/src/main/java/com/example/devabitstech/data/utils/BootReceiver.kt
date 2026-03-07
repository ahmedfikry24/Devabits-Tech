package com.example.devabitstech.data.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.devabitstech.domain.usecase.GetPrayerTimeByDateUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BootReceiver() : BroadcastReceiver(), KoinComponent {

    val scheduler: PrayerAlarmScheduler by inject()
    val getPrayerTimeByDateUseCase: GetPrayerTimeByDateUseCase by inject()
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {


            CoroutineScope(Dispatchers.IO).launch {
                val prayers = getPrayerTimeByDateUseCase()
                scheduler.scheduleAll(prayers)
            }
        }
    }
}