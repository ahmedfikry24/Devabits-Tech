package com.example.devabitstech.ui.screen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.devabitstech.ui.theme.Theme

@Composable
fun LoadingContent(
    modifier: Modifier = Modifier,
    isVisible: Boolean,
    enterTransition: EnterTransition = fadeIn(tween(500)) + scaleIn(tween(500)),
    exitTransition: ExitTransition = fadeOut(tween(500)) + scaleOut(tween(500)),
) {
    AnimatedVisibility(
        modifier = modifier.fillMaxSize(),
        visible = isVisible,
        enter = enterTransition,
        exit = exitTransition,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                color = Theme.colorScheme.primary,
                trackColor = Theme.colorScheme.notes,
                strokeWidth = 2.dp
            )
        }
    }
}