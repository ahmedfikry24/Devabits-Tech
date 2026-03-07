package com.example.devabitstech.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun DevabitsTechTheme(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalColorScheme provides lightColoScheme,
        LocalSpacing provides DevabitsSpacing,
        LocalRadius provides DevabitsRadius,
    ) {
        MaterialTheme(
            typography = typography,
            content = content
        )
    }
}