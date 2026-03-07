package com.example.devabitstech.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.devabitstech.DevabitsApplication

data class Radius(
    val xxs: Dp = 2.dp,
    val xs: Dp = 4.dp,
    val sm: Dp = 8.dp,
    val md: Dp = 12.dp,
    val lg: Dp = 16.dp,
    val xl: Dp = 24.dp,
)

internal val DevabitsRadius = Radius()

internal val LocalRadius = staticCompositionLocalOf { DevabitsRadius }