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
    val code: Int?,
    @SerialName("data")
    val `data`: Data?,
    @SerialName("status")
    val status: String?
) {
    @Serializable
    data class Data(
        @SerialName("date")
        val date: Date?,
        @SerialName("meta")
        val meta: Meta?,
        @SerialName("timings")
        val timings: Timings?
    )

    @Serializable
    data class Date(
        @SerialName("gregorian")
        val gregorian: Gregorian?,
        @SerialName("hijri")
        val hijri: Hijri?,
        @SerialName("readable")
        val readable: String?,
        @SerialName("timestamp")
        val timestamp: String?
    )

    @Serializable
    data class Gregorian(
        @SerialName("date")
        val date: String?,
        @SerialName("day")
        val day: String?,
        @SerialName("designation")
        val designation: Designation?,
        @SerialName("format")
        val format: String?,
        @SerialName("lunarSighting")
        val lunarSighting: Boolean?,
        @SerialName("month")
        val month: Month?,
        @SerialName("weekday")
        val weekday: Weekday?,
        @SerialName("year")
        val year: String?
    )

    @Serializable
    data class Hijri(
        @SerialName("date")
        val date: String?,
        @SerialName("day")
        val day: String?,
        @SerialName("designation")
        val designation: Designation?,
        @SerialName("format")
        val format: String?,
        @SerialName("method")
        val method: String?,
        @SerialName("month")
        val month: Month?,
        @SerialName("weekday")
        val weekday: Weekday?,
        @SerialName("year")
        val year: String?
    )

    @Serializable
    data class Designation(
        @SerialName("abbreviated")
        val abbreviated: String?,
        @SerialName("expanded")
        val expanded: String?
    )

    @Serializable
    data class Month(
        @SerialName("ar")
        val ar: String?,
        @SerialName("days")
        val days: Int?,
        @SerialName("en")
        val en: String?,
        @SerialName("number")
        val number: Int?
    )

    @Serializable
    data class Weekday(
        @SerialName("ar")
        val ar: String?,
        @SerialName("en")
        val en: String?
    )

    @Serializable
    data class Method(
        @SerialName("id")
        val id: Int?,
        @SerialName("location")
        val location: Location?,
        @SerialName("name")
        val name: String?,
        @SerialName("params")
        val params: Params?
    )

    @Serializable
    data class Meta(
        @SerialName("latitude")
        val latitude: Double?,
        @SerialName("latitudeAdjustmentMethod")
        val latitudeAdjustmentMethod: String?,
        @SerialName("longitude")
        val longitude: Double?,
        @SerialName("method")
        val method: Method?,
        @SerialName("midnightMode")
        val midnightMode: String?,
        @SerialName("offset")
        val offset: Offset?,
        @SerialName("school")
        val school: String?,
        @SerialName("timezone")
        val timezone: String?
    )

    @Serializable
    data class Location(
        @SerialName("latitude")
        val latitude: Double?,
        @SerialName("longitude")
        val longitude: Double?
    )

    @Serializable
    data class Params(
        @SerialName("Fajr")
        val fajr: Double?,
        @SerialName("Isha")
        val isha: Double?
    )

    @Serializable
    data class Offset(
        @SerialName("Asr")
        val asr: Int?,
        @SerialName("Dhuhr")
        val dhuhr: Int?,
        @SerialName("Fajr")
        val fajr: Int?,
        @SerialName("Imsak")
        val imsak: Int?,
        @SerialName("Isha")
        val isha: Int?,
        @SerialName("Maghrib")
        val maghrib: Int?,
        @SerialName("Midnight")
        val midnight: Int?,
        @SerialName("Sunrise")
        val sunrise: Int?,
        @SerialName("Sunset")
        val sunset: Int?
    )

    @Serializable
    data class Timings(
        @SerialName("Asr")
        val asr: String?,
        @SerialName("Dhuhr")
        val dhuhr: String?,
        @SerialName("Fajr")
        val fajr: String?,
        @SerialName("Firstthird")
        val firstthird: String?,
        @SerialName("Imsak")
        val imsak: String?,
        @SerialName("Isha")
        val isha: String?,
        @SerialName("Lastthird")
        val lastthird: String?,
        @SerialName("Maghrib")
        val maghrib: String?,
        @SerialName("Midnight")
        val midnight: String?,
        @SerialName("Sunrise")
        val sunrise: String?,
        @SerialName("Sunset")
        val sunset: String?
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