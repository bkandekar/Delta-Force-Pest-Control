package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme =
  darkColorScheme(
    primary = AmberAccent,
    onPrimary = CharcoalDark,
    primaryContainer = ForestGreenDark,
    onPrimaryContainer = SurfaceWhite,
    secondary = ForestGreenLight,
    onSecondary = CharcoalDark,
    background = CharcoalDark,
    onBackground = SurfaceWhite,
    surface = CharcoalMedium,
    onSurface = SurfaceWhite,
    surfaceVariant = Color(0xFF22302A),
    onSurfaceVariant = Color(0xFFD0DDD6)
  )

private val LightColorScheme =
  lightColorScheme(
    primary = ForestGreenPrimary,
    onPrimary = SurfaceWhite,
    primaryContainer = ForestGreenContainer,
    onPrimaryContainer = ForestGreenPrimary,
    secondary = AmberDark,
    onSecondary = SurfaceWhite,
    secondaryContainer = AmberContainer,
    onSecondaryContainer = CharcoalDark,
    tertiary = CharcoalDark,
    onTertiary = SurfaceWhite,
    background = BackgroundOffWhite,
    onBackground = CharcoalDark,
    surface = SurfaceWhite,
    onSurface = CharcoalDark,
    surfaceVariant = Color(0xFFF0F4F2),
    onSurfaceVariant = CharcoalMedium,
    outline = BorderLight
  )

@Composable
fun MyApplicationTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  dynamicColor: Boolean = false, // Preserve Forest Trust brand colors
  content: @Composable () -> Unit,
) {
  val colorScheme =
    when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
        val context = LocalContext.current
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }

      darkTheme -> DarkColorScheme
      else -> LightColorScheme
    }

  MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}

