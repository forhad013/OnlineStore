package com.greenrobotdev.onlinestore.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.defaultComponentContext
import com.greenrobotdev.onlinestore.di.appStorage
import com.greenrobotdev.onlinestore.screen.home.HomeScreen
import com.greenrobotdev.onlinestore.ui.AndroidAppTheme
import io.github.xxfast.decompose.LocalComponentContext

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        val rootComponentContext: DefaultComponentContext = defaultComponentContext()
        appStorage = filesDir.path

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
