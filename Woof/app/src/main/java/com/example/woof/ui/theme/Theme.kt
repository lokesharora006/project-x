package com.example.woof.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Grey50 = Color(0xFFF8F9FA)
val Grey900 = Color(0xFF202124)
val Grey700 = Color(0xFF5F6368)
val Green50 = Color(0xFFE6F4EA)
val Green100 = Color(0xFFCEEAD6)
val Cyan700 = Color(0xFF129EAF)
val Cyan900 = Color(0xFF202124)
val Grey100 = Color(0xFFF1F3F4)

@SuppressLint("ConflictingOnColor")
private val DarkColorPalette = darkColors(
    background = Cyan900,
    surface = Cyan700,
    onSurface = Color.White,
    primary = Grey900,
    onPrimary = Color.White,
    secondary = Grey100
)

private val LightColorPalette = lightColors(
    background = Green100,
    surface = Green50,
    onSurface = Grey900,
    primary = Grey50,
    onPrimary = Grey900,
    secondary = Grey700

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun WoofTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}