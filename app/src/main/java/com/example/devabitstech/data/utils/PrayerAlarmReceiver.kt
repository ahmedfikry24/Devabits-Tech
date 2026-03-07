package com.example.devabitstech.data.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class PrayerAlarmReceiver : BroadcastReceiver(), KoinComponent {

    private val notificationService: PrayerNotificationService by inject()
    override fun onReceive(context: Context, intent: Intent) {
        val prayerName = intent.getStringExtra(PrayerAlarmScheduler.EXTRA_PRAYER_NAME) ?: return
        val prayerTime = intent.getStringExtra(PrayerAlarmScheduler.EXTRA_PRAYER_TIME) ?: ""
        val prayerId = intent.getLongExtra(PrayerAlarmScheduler.EXTRA_PRAYER_ID, 0L)

        notificationService.showPrayerNotification(
            prayerId = prayerId,
            prayerName = prayerName,
            prayerTime = prayerTime
        )
    }
}