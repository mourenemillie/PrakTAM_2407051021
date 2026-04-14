package com.example.praktam_2407051021.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val AppColorScheme = lightColorScheme(
    primary = BrownPrimary,
    secondary = BrownSecondary,
    background = BeigeBackground,
    surface = WhiteSurface,
    onPrimary = WhiteSurface,
    onBackground = TextPrimary,
    onSurface = TextSecondary,
    onSurfaceVariant = TextPrimary,
    tertiary = RedFavorite
)

@Composable
fun PraktiktamTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // Karena ini tema spesifik project mahasiswa, kita langsung paksakan skema warnanya
    MaterialTheme(
        colorScheme = AppColorScheme,
        typography = AppTypography,
        content = content
    )
}