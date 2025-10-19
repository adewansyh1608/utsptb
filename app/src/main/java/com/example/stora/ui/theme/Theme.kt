package com.example.stora.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Palet warna untuk mode gelap (Biarkan default atau sesuaikan nanti)
private val DarkColorScheme = darkColorScheme(
    primary = StoraBlueLight,
    secondary = StoraYellow,
    tertiary = StoraBlue,
    background = Color(0xFF121212),
    surface = Color(0xFF121212),
    onPrimary = StoraBlueDark,
    onSecondary = StoraBlueDark,
    onTertiary = StoraWhite,
    onBackground = StoraWhite,
    onSurface = StoraWhite,
)

// Palet warna untuk mode terang (Sesuai desain baru)
private val LightColorScheme = lightColorScheme(
    primary = StoraBlueDark,
    secondary = StoraBlue,
    tertiary = StoraYellow,
    background = StoraBlueDark,
    surface = StoraWhite,
    onPrimary = StoraWhite,
    onSecondary = StoraWhite,
    onTertiary = StoraBlueDark,
    onBackground = StoraWhite,
    onSurface = StoraBlueDark
)

@Composable
fun STORATheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            // Mengatur warna status bar agar sesuai dengan background biru tua
            window.statusBarColor = StoraBlueDark.toArgb()
            // Mengatur ikon status bar (jam, sinyal) menjadi terang
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography, // Asumsi file Typography.kt Anda sudah ada
        content = content
    )
}