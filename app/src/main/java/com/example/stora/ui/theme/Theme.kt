package com.example.stora.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.compose.ui.graphics.Color
// Palet warna untuk mode gelap
private val DarkColorScheme = darkColorScheme(
    primary = DarkPurple,
    secondary = AccentCyan,
    tertiary = Pink80,
    background = Color(0xFF121212),
    surface = Color(0xFF121212),
)

// Palet warna untuk mode terang
private val LightColorScheme = lightColorScheme(
    primary = PrimaryPurple,
    secondary = AccentCyan,
    tertiary = Pink40,
    background = BackgroundGrey,
    surface = Color(0xFFFFFBFE),
)

@Composable
fun STORATheme( // Nama fungsi tema diubah
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
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}