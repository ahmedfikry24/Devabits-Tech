package com.example.devabitstech.data.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.example.devabitstech.domain.entity.Prayer
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class PrayerAlarmScheduler(private val context: Context) {

    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    fun scheduleAll(prayers: List<Prayer>) {
        prayers.forEach { prayer -> schedule(prayer) }
    }

    private fun schedule(prayer: Prayer) {
        val triggerAtMillis = getPrayerTriggerMillis(
            dateStr = prayer.date.gregorian,
            timeStr = prayer.time
        ) ?: return

        if (triggerAtMillis <= System.currentTimeMillis()) return

        val intent = Intent(context, PrayerAlarmReceiver::class.java).apply {
            putExtra(EXTRA_PRAYER_NAME, prayer.name)
            putExtra(EXTRA_PRAYER_TIME, prayer.time)
            putExtra(EXTRA_PRAYER_ID, prayer.id ?: 0L)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            prayer.id?.toInt() ?: prayer.name.hashCode(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (alarmManager.canScheduleExactAlarms()) {
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    triggerAtMillis,
                    pendingIntent
                )
            }
        } else {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                triggerAtMillis,
                pendingIntent
            )
        }
    }

    fun cancel(prayer: Prayer) {
        val intent = Intent(context, PrayerAlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            prayer.id?.toInt() ?: prayer.name.hashCode(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.cancel(pendingIntent)
    }

    fun cancelAll(prayers: List<Prayer>) {
        prayers.forEach { cancel(it) }
    }

    private fun getPrayerTriggerMillis(dateStr: String, timeStr: String): Long? {
        return try {
            val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")
            val dateTime = LocalDateTime.parse("$dateStr $timeStr", formatter)
            dateTime
                .atZone(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli()
        } catch (e: Exception) {
            null
        }
    }

    companion object {
        const val EXTRA_PRAYER_NAME = "extra_prayer_name"
        const val EXTRA_PRAYER_TIME = "extra_prayer_time"
        const val EXTRA_PRAYER_ID = "extra_prayer_id"
    }
}