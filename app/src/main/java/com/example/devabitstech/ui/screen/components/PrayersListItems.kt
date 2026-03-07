package com.example.devabitstech.ui.screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.devabitstech.domain.entity.Prayer
import com.example.devabitstech.ui.theme.Theme

@Composable
fun PrayersListItems(
    prayers: List<Prayer>,
    upcomingPrayer: Prayer,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(Theme.spacing.space16),
        contentPadding = PaddingValues(Theme.spacing.space16)
    ) {
        items(prayers, key = { it.id ?: it.name }) { prayer ->
            PrayerItem(
                prayer,
                isUpcoming = prayer == upcomingPrayer
            )
        }
    }
}