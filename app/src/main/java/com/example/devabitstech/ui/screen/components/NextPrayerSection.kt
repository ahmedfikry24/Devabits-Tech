package com.example.devabitstech.ui.screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.devabitstech.domain.entity.Prayer
import com.example.devabitstech.ui.theme.Theme
import kotlinx.coroutines.delay
import java.time.Duration
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun NextPrayerSection(
    upcomingPrayer: Prayer,
    modifier: Modifier = Modifier
) {
    val imageHeight = LocalConfiguration.current.screenHeightDp.dp * 0.25f
    Box(
        modifier
            .fillMaxWidth()
            .height(imageHeight),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(com.example.devabitstech.R.drawable.afternoon),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.25f),
                            Color.Black.copy(alpha = 0.65f)
                        )
                    )
                )
        )


        var tick by remember { mutableLongStateOf(System.currentTimeMillis()) }

        LaunchedEffect(Unit) {
            while (true) {
                delay(1000L)
                tick = System.currentTimeMillis()
            }
        }

        val remainingTime = remember(upcomingPrayer.time, tick) {
            calculateRemainingTime(upcomingPrayer.time)
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(Theme.spacing.space16)
        ) {
            Text(
                text = upcomingPrayer.name,
                style = MaterialTheme.typography.headlineLarge.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                )
            )

            Text(
                text = remainingTime,
                style = MaterialTheme.typography.titleMedium.copy(
                    color = Theme.colorScheme.white.copy(alpha = 0.90f),
                    fontWeight = FontWeight.SemiBold,
                )
            )

            Box(
                modifier = Modifier
                    .background(
                        color = Color.White.copy(alpha = 0.15f),
                        shape = RoundedCornerShape(50.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = Color.White.copy(alpha = 0.3f),
                        shape = RoundedCornerShape(50.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 4.dp)
            ) {
                Text(
                    text = upcomingPrayer.time,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
        }
    }
}


private fun calculateRemainingTime(prayerTime: String): String {
    return try {
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        val now = LocalTime.now()
        val prayer = LocalTime.parse(prayerTime.trim(), formatter)

        var diff = Duration.between(now, prayer)

        if (diff.isNegative) {
            diff = diff.plusHours(24)
        }

        val hours = diff.toHours()
        val minutes = (diff.toMinutes() % 60)
        val seconds = (diff.seconds % 60)

        when {
            hours > 0 -> "%02dh %02dm %02ds".format(hours, minutes, seconds)
            else -> "%02dm %02ds".format(minutes, seconds)
        }
    } catch (e: Exception) {
        prayerTime
    }
}