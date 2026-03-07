package com.example.devabitstech.ui.screen.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.filled.WbTwilight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.devabitstech.domain.entity.Prayer
import com.example.devabitstech.ui.theme.Theme

@Composable
fun PrayerItem(
    prayer: Prayer,
    isUpcoming: Boolean,
    modifier: Modifier = Modifier
) {
    val borderColor = Theme.colorScheme.primary
    val animatedBorderAlpha by animateFloatAsState(
        targetValue = if (isUpcoming) 1f else 0f,
        animationSpec = tween(durationMillis = 600),
        label = "borderAlpha"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(Theme.spacing.space16))
            .then(
                if (isUpcoming) Modifier.border(
                    width = 2.dp,
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            borderColor.copy(alpha = animatedBorderAlpha),
                            borderColor.copy(alpha = animatedBorderAlpha * 0.4f)
                        )
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) else Modifier
            )
            .background(
                color = if (isUpcoming)
                    Theme.colorScheme.primary.copy(alpha = 0.08f)
                else
                    Theme.colorScheme.notes.copy(alpha = 0.5f),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = Theme.spacing.space16, vertical = Theme.spacing.space8)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(Theme.spacing.space12)
            ) {
                Box(
                    modifier = Modifier
                        .size(46.dp)
                        .background(
                            color = if (isUpcoming)
                                Theme.colorScheme.primary.copy(alpha = 0.15f)
                            else
                                Theme.colorScheme.border.copy(alpha = 0.06f),
                            shape = RoundedCornerShape(12.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = prayerIcon(prayer.name),
                        contentDescription = null,
                        tint = if (isUpcoming)
                            Theme.colorScheme.primary
                        else
                            Theme.colorScheme.secondary.copy(alpha = 0.45f),
                        modifier = Modifier.size(20.dp)
                    )
                }

                Column(verticalArrangement = Arrangement.spacedBy(Theme.spacing.space2)) {
                    Text(
                        text = prayer.name,
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = if (isUpcoming) FontWeight.Bold else FontWeight.Medium,
                            color = if (isUpcoming)
                                MaterialTheme.colorScheme.onSurface
                            else
                                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                        )
                    )
                    if (isUpcoming) {
                        Text(
                            text = "Upcoming",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(Theme.spacing.space8)
            ) {
                Text(
                    text = prayer.time,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = if (isUpcoming) FontWeight.Bold else FontWeight.Normal,
                        color = if (isUpcoming)
                            Theme.colorScheme.primary
                        else
                            Theme.colorScheme.secondary.copy(alpha = 0.55f)
                    )
                )
            }
        }
    }
}


private fun prayerIcon(name: String): ImageVector = when (name.lowercase()) {
    "fajr" -> Icons.Default.DarkMode
    "sunrise" -> Icons.Default.WbTwilight
    "dhuhr" -> Icons.Default.WbSunny
    "asr" -> Icons.Default.LightMode
    "maghrib" -> Icons.Default.WbTwilight
    "isha" -> Icons.Default.NightsStay
    else -> Icons.Default.AccessTime
}