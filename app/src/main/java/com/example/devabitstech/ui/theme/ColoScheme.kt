package com.example.devabitstech.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class ColoScheme(
    val primary: Color,
    val secondary: Color,
    val background: Color,
    val button: Color,
    val buttonDisabled: Color,
    val border: Color,
    val borderDisabled: Color,
    val notes: Color,
    val error: Color,
    val errorLight: Color,
    val success: Color,
    val white: Color,
)

internal val lightColoScheme = ColoScheme(
    primary = Color(0xFF2F4C67),
    secondary = Color(0xFF48627A),
    background = Color(0xFFF2F2F2),
    button = Color(0xFF2B77C2),
    buttonDisabled = Color(0XFF686868),
    border = Color(0xFF48627A),
    borderDisabled = Color(0xFFDEE1F0),
    notes = Color(0xFFA6B4C2),
    error = Color(0xFFAB2937),
    errorLight = Color(0xFFE85C6C),
    success = Color(0xFF3E9958),
    white = Color(0xFFFFFFFF),
)

internal val LocalColorScheme = staticCompositionLocalOf { lightColoScheme }