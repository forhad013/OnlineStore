package com.greenrobotdev.onlinestore

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.greenrobotdev.onlinestore.di.appStorage
import com.greenrobotdev.onlinestore.screen.home.HomeScreen
import io.github.xxfast.decompose.LocalComponentContext
import platform.Foundation.NSHomeDirectory
import platform.UIKit.UIViewController

fun Main(): UIViewController = ComposeUIViewController {
    val lifecycle = LifecycleRegistry()
    val rootComponentContext = DefaultComponentContext(lifecycle = lifecycle)
    appStorage = NSHomeDirectory()

    CompositionLocalProvider(LocalComponentContext provides rootComponentContext) {
        MaterialTheme {
            Surface(tonalElevation = 5.dp) {
                HomeScreen()
            }
        }
    }
}



