package com.example.devabitstech.domain.usecase

import com.example.devabitstech.domain.entity.Prayer
import com.example.devabitstech.domain.repository.PrayerRepository
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class GetPrayerTimeByDateUseCase(
    private val repository: PrayerRepository
) {

    suspend operator fun invoke(date: String = getTodayDate()): List<Prayer> {
        return repository.getPrayerTimesByDate(date)
    }
}


fun getTodayDate(): String {
    val current = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    return current.format(formatter)
}