package com.example.devabitstech.domain.entity

data class Prayer(
    val id: Long?,
    val name: String,
    val time: String,
    val date: PrayerDate
) {
    companion object {
        val empty = Prayer(
            id = null,
            name = "",
            time = "",
            date = PrayerDate(
                readable = "",
                timestamp = "",
                hijri = HijriDate(
                    date = "",
                    format = "",
                    day = "",
                    weekday = DateNameInfo(en = "", ar = ""),
                    month = DateNameInfo(en = "", ar = ""),
                    year = ""
                ),
                gregorian = ""
            )
        )
    }
}

data class PrayerDate(
    val readable: String,
    val timestamp: String,
    val hijri: HijriDate,
    val gregorian: String
)

data class HijriDate(
    val date: String,
    val format: String,
    val day: String,
    val weekday: DateNameInfo,
    val month: DateNameInfo,
    val year: String
)

data class DateNameInfo(
    val en: String,
    val ar: String
)

