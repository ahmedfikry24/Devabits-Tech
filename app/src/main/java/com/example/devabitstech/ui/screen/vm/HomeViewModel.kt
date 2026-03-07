package com.example.devabitstech.ui.screen.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.devabitstech.data.utils.PrayerAlarmScheduler
import com.example.devabitstech.domain.entity.DateNameInfo
import com.example.devabitstech.domain.entity.HijriDate
import com.example.devabitstech.domain.entity.Prayer
import com.example.devabitstech.domain.entity.PrayerDate
import com.example.devabitstech.domain.usecase.GetPrayerTimeByDateUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class HomeViewModel(
    private val getPrayerTimeByDateUseCase: GetPrayerTimeByDateUseCase,
    private val scheduler: PrayerAlarmScheduler
) : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state: StateFlow<HomeUiState> = _state.asStateFlow()

    init {
        initDate()
    }

    private fun <T> tryToDoOperation(
        onStart: () -> Unit = {},
        operation: suspend () -> T,
        onSuccess: (T) -> Unit = {},
        onError: (Throwable) -> Unit = {}
    ) {
        val exceptionHandler = CoroutineExceptionHandler { _, exception ->
            onError(exception)
        }
        onStart()
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val result = operation()
            onSuccess(result)
        }
    }

    fun initDate() {
        tryToDoOperation(
            onStart = { _state.update { it.copy(screenState = ScreenState.LOADING) } },
            operation = { getPrayerTimeByDateUseCase() },
            onSuccess = { prayers ->
                _state.update {
                    it.copy(
                        screenState = ScreenState.VISIBLE,
                        prayers = prayers,
                        upcomingPrayer = getUpcomingPrayer(prayers)
                    )
                }
                scheduler.scheduleAll(prayers)
            },
            onError = { error ->
                _state.update {
                    it.copy(
                        screenState = ScreenState.ERROR,
                        errorMessage = error.message
                    )
                }
            }
        )
    }

    private fun getUpcomingPrayer(prayers: List<Prayer>): Prayer {
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        val now = LocalTime.now()

        return prayers
            .filter { prayer ->
                runCatching {
                    LocalTime.parse(prayer.time.trim(), formatter).isAfter(now)
                }.getOrDefault(false)
            }
            .minBy { prayer ->
                Duration.between(now, LocalTime.parse(prayer.time.trim(), formatter)).toSeconds()
            }
    }
}