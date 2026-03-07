package com.example.devabitstech.data.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.devabitstech.domain.entity.DateNameInfo
import com.example.devabitstech.domain.entity.HijriDate
import com.example.devabitstech.domain.entity.Prayer
import com.example.devabitstech.domain.entity.PrayerDate

@Entity
data class PrayerEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val prayerName: String,
    val prayerTime: String,

    @Embedded(prefix = "date_")
    val date: PrayerDateEntity
)

data class PrayerDateEntity(
    val readable: String,
    val timestamp: String,
    val gregorian: String,
    @Embedded(prefix = "hijri_")
    val hijri: HijriDateEntity
)

data class HijriDateEntity(
    val date: String,
    val format: String,
    val day: String,
    @Embedded(prefix = "weekday_")
    val weekday: DateNameInfoEntity,
    @Embedded(prefix = "month_")
    val month: DateNameInfoEntity,
    val year: String
)

data class DateNameInfoEntity(
    val en: String,
    val ar: String
)

fun Prayer.toRoomEntity(): PrayerEntity {
    return PrayerEntity(
        prayerName = this.name,
        prayerTime = this.time,
        date = PrayerDateEntity(
            readable = this.date.readable,
            timestamp = this.date.timestamp,
            gregorian = this.date.gregorian,
            hijri = HijriDateEntity(
                date = this.date.hijri.date,
                format = this.date.hijri.format,
                day = this.date.hijri.day,
                year = this.date.hijri.year,
                weekday = DateNameInfoEntity(
                    this.date.hijri.weekday.en,
                    this.date.hijri.weekday.ar
                ),
                month = DateNameInfoEntity(this.date.hijri.month.en, this.date.hijri.month.ar)
            )
        )
    )
}


fun PrayerEntity.toDomain(): Prayer = Prayer(
    id = id,
    name = prayerName,
    time = prayerTime,
    date = PrayerDate(
        readable = this.date.readable,
        timestamp = this.date.timestamp,
        gregorian = this.date.gregorian,
        hijri = HijriDate(
            date = this.date.hijri.date,
            format = this.date.hijri.format,
            day = this.date.hijri.day,
            weekday = DateNameInfo(
                en = this.date.hijri.weekday.en,
                ar = this.date.hijri.weekday.ar
            ),
            month = DateNameInfo(
                en = this.date.hijri.month.en,
                ar = this.date.hijri.month.ar
            ),
            year = this.date.hijri.year
        )
    )
)
