package com.example.devabitstech.ui.screen.vm

import com.example.devabitstech.domain.entity.Prayer

data class HomeUiState(
    val screenState: ScreenState = ScreenState.LOADING,
    val errorMessage: String? = null,
    val prayers: List<Prayer> = emptyList(),
    val upcomingPrayer: Prayer = Prayer.empty
)

enum class ScreenState {
    LOADING,
    VISIBLE,
    ERROR
}
