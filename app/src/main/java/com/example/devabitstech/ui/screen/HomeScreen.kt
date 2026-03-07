package com.example.devabitstech.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.devabitstech.ui.screen.components.FailureDialog
import com.example.devabitstech.ui.screen.components.LoadingContent
import com.example.devabitstech.ui.screen.components.NextPrayerSection
import com.example.devabitstech.ui.screen.components.PrayerItem
import com.example.devabitstech.ui.screen.components.VisibleContent
import com.example.devabitstech.ui.screen.vm.HomeUiState
import com.example.devabitstech.ui.screen.vm.HomeViewModel
import com.example.devabitstech.ui.screen.vm.ScreenState
import com.example.devabitstech.ui.theme.Theme
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen() {
    val viewModel = koinViewModel<HomeViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    HomeContent(
        state = state,
    )
}

@Composable
private fun HomeContent(state: HomeUiState) {
    LoadingContent(isVisible = state.screenState == ScreenState.LOADING)
    VisibleContent(isVisible = state.screenState == ScreenState.VISIBLE) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(Theme.spacing.space16)
        ) {
            NextPrayerSection(state.upcomingPrayer)
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(Theme.spacing.space16),
                contentPadding = PaddingValues(Theme.spacing.space16)
            ) {
                items(state.prayers, key = { it.id ?: it.name }) { prayer ->
                    PrayerItem(
                        prayer,
                        isUpcoming = prayer == state.upcomingPrayer
                    )
                }
            }
        }
    }
    if (state.screenState == ScreenState.ERROR) {
        FailureDialog(
            title = "Something went wrong",
            description = state.errorMessage ?: "can not recognize the error",
            onDismiss = {},
            onButtonClick = {}
        )
    }
}