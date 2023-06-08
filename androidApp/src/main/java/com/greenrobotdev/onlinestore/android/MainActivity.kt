package com.greenrobotdev.onlinestore.android

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.defaultComponentContext
import com.greenrobotdev.onlinestore.screen.home.HomeScreen
import com.greenrobotdev.onlinestore.ui.AndroidAppTheme
import com.greenrobotdev.onlinestore.ui.md_theme_dark_background
import com.greenrobotdev.onlinestore.ui.md_theme_dark_error
import com.greenrobotdev.onlinestore.ui.md_theme_dark_errorContainer
import com.greenrobotdev.onlinestore.ui.md_theme_dark_inverseOnSurface
import com.greenrobotdev.onlinestore.ui.md_theme_dark_inversePrimary
import com.greenrobotdev.onlinestore.ui.md_theme_dark_inverseSurface
import com.greenrobotdev.onlinestore.ui.md_theme_dark_onBackground
import com.greenrobotdev.onlinestore.ui.md_theme_dark_onError
import com.greenrobotdev.onlinestore.ui.md_theme_dark_onErrorContainer
import com.greenrobotdev.onlinestore.ui.md_theme_dark_onPrimary
import com.greenrobotdev.onlinestore.ui.md_theme_dark_onPrimaryContainer
import com.greenrobotdev.onlinestore.ui.md_theme_dark_onSecondary
import com.greenrobotdev.onlinestore.ui.md_theme_dark_onSecondaryContainer
import com.greenrobotdev.onlinestore.ui.md_theme_dark_onSurface
import com.greenrobotdev.onlinestore.ui.md_theme_dark_onSurfaceVariant
import com.greenrobotdev.onlinestore.ui.md_theme_dark_onTertiary
import com.greenrobotdev.onlinestore.ui.md_theme_dark_onTertiaryContainer
import com.greenrobotdev.onlinestore.ui.md_theme_dark_outline
import com.greenrobotdev.onlinestore.ui.md_theme_dark_outlineVariant
import com.greenrobotdev.onlinestore.ui.md_theme_dark_primary
import com.greenrobotdev.onlinestore.ui.md_theme_dark_primaryContainer
import com.greenrobotdev.onlinestore.ui.md_theme_dark_scrim
import com.greenrobotdev.onlinestore.ui.md_theme_dark_secondary
import com.greenrobotdev.onlinestore.ui.md_theme_dark_secondaryContainer
import com.greenrobotdev.onlinestore.ui.md_theme_dark_surface
import com.greenrobotdev.onlinestore.ui.md_theme_dark_surfaceTint
import com.greenrobotdev.onlinestore.ui.md_theme_dark_surfaceVariant
import com.greenrobotdev.onlinestore.ui.md_theme_dark_tertiary
import com.greenrobotdev.onlinestore.ui.md_theme_dark_tertiaryContainer
import com.greenrobotdev.onlinestore.ui.md_theme_light_background
import com.greenrobotdev.onlinestore.ui.md_theme_light_error
import com.greenrobotdev.onlinestore.ui.md_theme_light_errorContainer
import com.greenrobotdev.onlinestore.ui.md_theme_light_inverseOnSurface
import com.greenrobotdev.onlinestore.ui.md_theme_light_inversePrimary
import com.greenrobotdev.onlinestore.ui.md_theme_light_inverseSurface
import com.greenrobotdev.onlinestore.ui.md_theme_light_onBackground
import com.greenrobotdev.onlinestore.ui.md_theme_light_onError
import com.greenrobotdev.onlinestore.ui.md_theme_light_onErrorContainer
import com.greenrobotdev.onlinestore.ui.md_theme_light_onPrimary
import com.greenrobotdev.onlinestore.ui.md_theme_light_onPrimaryContainer
import com.greenrobotdev.onlinestore.ui.md_theme_light_onSecondary
import com.greenrobotdev.onlinestore.ui.md_theme_light_onSecondaryContainer
import com.greenrobotdev.onlinestore.ui.md_theme_light_onSurface
import com.greenrobotdev.onlinestore.ui.md_theme_light_onSurfaceVariant
import com.greenrobotdev.onlinestore.ui.md_theme_light_onTertiary
import com.greenrobotdev.onlinestore.ui.md_theme_light_onTertiaryContainer
import com.greenrobotdev.onlinestore.ui.md_theme_light_outline
import com.greenrobotdev.onlinestore.ui.md_theme_light_outlineVariant
import com.greenrobotdev.onlinestore.ui.md_theme_light_primary
import com.greenrobotdev.onlinestore.ui.md_theme_light_primaryContainer
import com.greenrobotdev.onlinestore.ui.md_theme_light_scrim
import com.greenrobotdev.onlinestore.ui.md_theme_light_secondary
import com.greenrobotdev.onlinestore.ui.md_theme_light_secondaryContainer
import com.greenrobotdev.onlinestore.ui.md_theme_light_surface
import com.greenrobotdev.onlinestore.ui.md_theme_light_surfaceTint
import com.greenrobotdev.onlinestore.ui.md_theme_light_surfaceVariant
import com.greenrobotdev.onlinestore.ui.md_theme_light_tertiary
import com.greenrobotdev.onlinestore.ui.md_theme_light_tertiaryContainer
import com.greenrobotdev.onlinestore.ui.shapes
import com.greenrobotdev.onlinestore.ui.typography
import io.github.xxfast.decompose.LocalComponentContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        val rootComponentContext: DefaultComponentContext = defaultComponentContext()

        setContent {
            CompositionLocalProvider(LocalComponentContext provides rootComponentContext) {
                AndroidAppTheme {
                    Surface(tonalElevation = 5.dp) {
                        HomeScreen()
                    }
                }
            }
        }
    }
}
