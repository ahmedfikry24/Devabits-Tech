package com.example.devabitstech.data.remote.dto


import com.example.devabitstech.domain.entity.DateNameInfo
import com.example.devabitstech.domain.entity.HijriDate
import com.example.devabitstech.domain.entity.Prayer
import com.example.devabitstech.domain.entity.PrayerDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PrayerTimesDto(
    @SerialName("code")
    val code: Int? = null,
    @SerialName("data")
    val data: Data? = null,
    @SerialName("status")
    val status: String? = null
) {
    @Serializable
    data class Data(
        @SerialName("date")
        val date: Date? = null,
        @SerialName("meta")
        val meta: Meta? = null,
        @SerialName("timings")
        val timings: Timings? = null
    )

    @Serializable
    data class Date(
        @SerialName("gregorian")
        val gregorian: Gregorian? = null,
        @SerialName("hijri")
        val hijri: Hijri? = null,
        @SerialName("readable")
        val readable: String? = null,
        @SerialName("timestamp")
        val timestamp: String? = null
    )

    @Serializable
    data class Gregorian(
        @SerialName("date")
        val date: String? = null,
        @SerialName("day")
        val day: String? = null,
        @SerialName("designation")
        val designation: Designation? = null,
        @SerialName("format")
        val format: String? = null,
        @SerialName("lunarSighting")
        val lunarSighting: Boolean? = null,
        @SerialName("month")
        val month: Month? = null,
        @SerialName("weekday")
        val weekday: Weekday? = null,
        @SerialName("year")
        val year: String? = null
    )

    @Serializable
    data class Hijri(
        @SerialName("date")
        val date: String? = null,
        @SerialName("day")
        val day: String? = null,
        @SerialName("designation")
        val designation: Designation? = null,
        @SerialName("format")
        val format: String? = null,
        @SerialName("method")
        val method: String? = null,
        @SerialName("month")
        val month: Month? = null,
        @SerialName("weekday")
        val weekday: Weekday? = null,
        @SerialName("year")
        val year: String? = null
    )

    @Serializable
    data class Designation(
        @SerialName("abbreviated")
        val abbreviated: String? = null,
        @SerialName("expanded")
        val expanded: String? = null
    )

    @Serializable
    data class Month(
        @SerialName("ar")
        val ar: String? = null,
        @SerialName("days")
        val days: Int? = null,
        @SerialName("en")
        val en: String? = null,
        @SerialName("number")
        val number: Int? = null
    )

    @Serializable
    data class Weekday(
        @SerialName("ar")
        val ar: String? = null,
        @SerialName("en")
        val en: String? = null
    )

    @Serializable
    data class Method(
        @SerialName("id")
        val id: Int? = null,
        @SerialName("location")
        val location: Location? = null,
        @SerialName("name")
        val name: String? = null,
        @SerialName("params")
        val params: Params? = null
    )

    @Serializable
    data class Meta(
        @SerialName("latitude")
        val latitude: Double? = null,
        @SerialName("latitudeAdjustmentMethod")
        val latitudeAdjustmentMethod: String? = null,
        @SerialName("longitude")
        val longitude: Double? = null,
        @SerialName("method")
        val method: Method? = null,
        @SerialName("midnightMode")
        val midnightMode: String? = null,
        @SerialName("offset")
        val offset: Offset? = null,
        @SerialName("school")
        val school: String? = null,
        @SerialName("timezone")
        val timezone: String? = null
    )

    @Serializable
    data class Location(
        @SerialName("latitude")
        val latitude: Double? = null,
        @SerialName("longitude")
        val longitude: Double? = null
    )

    @Serializable
    data class Params(
        @SerialName("Fajr")
        val fajr: Double? = null,
        @SerialName("Isha")
        val isha: Double? = null
    )

    @Serializable
    data class Offset(
        @SerialName("Asr")
        val asr: Int? = null,
        @SerialName("Dhuhr")
        val dhuhr: Int? = null,
        @SerialName("Fajr")
        val fajr: Int? = null,
        @SerialName("Imsak")
        val imsak: Int? = null,
        @SerialName("Isha")
        val isha: Int? = null,
        @SerialName("Maghrib")
        val maghrib: Int? = null,
        @SerialName("Midnight")
        val midnight: Int? = null,
        @SerialName("Sunrise")
        val sunrise: Int? = null,
        @SerialName("Sunset")
        val sunset: Int? = null
    )

    @Serializable
    data class Timings(
        @SerialName("Asr")
        val asr: String? = null,
        @SerialName("Dhuhr")
        val dhuhr: String? = null,
        @SerialName("Fajr")
        val fajr: String? = null,
        @SerialName("Firstthird")
        val firstthird: String? = null,
        @SerialName("Imsak")
        val imsak: String? = null,
        @SerialName("Isha")
        val isha: String? = null,
        @SerialName("Lastthird")
        val lastthird: String? = null,
        @SerialName("Maghrib")
        val maghrib: String? = null,
        @SerialName("Midnight")
        val midnight: String? = null,
        @SerialName("Sunrise")
        val sunrise: String? = null,
        @SerialName("Sunset")
        val sunset: String? = null
    )
}


fun PrayerTimesDto.toEntityList(): List<Prayer> {
    val data = this.data ?: return emptyList()
    val timings = data.timings ?: return emptyList()
    val dateInfo = data.date


    val prayerDate = PrayerDate(
        readable = dateInfo?.readable.orEmpty(),
        timestamp = dateInfo?.timestamp.orEmpty(),
        gregorian = dateInfo?.gregorian?.date.orEmpty(),
        hijri = HijriDate(
            date = dateInfo?.hijri?.date.orEmpty(),
            format = dateInfo?.hijri?.format.orEmpty(),
            day = dateInfo?.hijri?.day.orEmpty(),
            year = dateInfo?.hijri?.year.orEmpty(),
            weekday = DateNameInfo(
                en = dateInfo?.hijri?.weekday?.en.orEmpty(),
                ar = dateInfo?.hijri?.weekday?.ar.orEmpty()
            ),
            month = DateNameInfo(
                en = dateInfo?.hijri?.month?.en.orEmpty(),
                ar = dateInfo?.hijri?.month?.ar.orEmpty()
            )
        )
    )

    val timingsMap = listOf(
        "Fajr" to timings.fajr,
        "Sunrise" to timings.sunrise,
        "Dhuhr" to timings.dhuhr,
        "Asr" to timings.asr,
        "Maghrib" to timings.maghrib,
        "Isha" to timings.isha
    )

    return timingsMap.mapIndexed { _, (name, time) ->
        Prayer(
            id = null,
            name = name,
            time = time.orEmpty(),
            date = prayerDate
        )
    }
}