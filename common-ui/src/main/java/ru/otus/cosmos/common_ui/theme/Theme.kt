package ru.otus.cosmos.common_ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.platform.LocalContext

private val darkScheme = darkColorScheme(
    primary = ColorPrimary,
    background = DarkGray,
    onBackground = CosmosWhite,
    onPrimary = DarkGray
)

private val lightScheme = lightColorScheme(
    primary = ColorPrimary,
    background = CosmosWhite,
    onBackground = CosmosDarkGray,
    onPrimary = DarkGray
)

@Composable
fun CosmosTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colorScheme = when {
        darkTheme -> darkScheme
        else -> lightScheme
    }
    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography,
        content = content
    )
}
