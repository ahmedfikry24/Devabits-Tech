package com.example.devabitstech.ui.screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.devabitstech.R
import com.example.devabitstech.ui.theme.Theme

@Composable
fun FailureDialog(
    title: String,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    description: String? = null,
    isButtonVisible: Boolean = true,
    buttonText: String = stringResource(R.string.retry),
    onButtonClick: () -> Unit = {},
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = modifier,
            shape = RoundedCornerShape(Theme.radius.xl),
            colors = CardDefaults.cardColors(containerColor = Theme.colorScheme.white),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column(
                modifier = modifier
                    .background(Theme.colorScheme.white)
                    .padding(Theme.spacing.space16),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .size(48.dp)
                        .padding(top = Theme.spacing.space8),
                    imageVector = ImageVector.vectorResource(R.drawable.ic_failure),
                    contentDescription = null
                )
                Spacer(Modifier.padding(vertical = Theme.spacing.space8))
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
                description?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                        color = Color.DarkGray,
                        textAlign = TextAlign.Center
                    )
                }
                if (isButtonVisible) {
                    Spacer(Modifier.padding(vertical = Theme.spacing.space16))
                    TextButton(
                        modifier = modifier,
                        shape = RoundedCornerShape(Theme.radius.lg),
                        colors = ButtonDefaults.textButtonColors(
                            containerColor = Theme.colorScheme.button,
                            contentColor = Theme.colorScheme.white
                        ),
                        onClick = onButtonClick
                    ) {
                        Text(
                            text = buttonText,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}