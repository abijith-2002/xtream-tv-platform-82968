package com.example.android_tv_frontend.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColors: ColorScheme = darkColorScheme(
    primary = AppColors.Accent,
    onPrimary = Color.White,
    background = AppColors.Background,
    onBackground = AppColors.PrimaryText,
    surface = AppColors.Surface,
    onSurface = AppColors.PrimaryText,
    secondary = AppColors.AccentDark
)

/**
 * App-wide Compose theme for TV.
 */
@Composable
fun XTreamTVTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColors,
        typography = AppTypography,
        content = content
    )
}
