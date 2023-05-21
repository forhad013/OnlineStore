package com.greenrobotdev.onlinestore

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import io.github.xxfast.decompose.LocalComponentContext
import platform.UIKit.UIViewController
import com.greenrobotdev.onlinestore.screen.home.HomeScreen
import platform.Foundation.NSHomeDirectory

//fun Main(): UIViewController {
//  return ComposeUIViewController {
//        MaterialTheme {
//            HomeScreen()
//        }
//    }
//}

fun Main(): UIViewController = ComposeUIViewController {
    val lifecycle = LifecycleRegistry()
    val rootComponentContext = DefaultComponentContext(lifecycle = lifecycle)

    CompositionLocalProvider(LocalComponentContext provides rootComponentContext) {
        MaterialTheme {
            HomeScreen()
        }
    }
}

