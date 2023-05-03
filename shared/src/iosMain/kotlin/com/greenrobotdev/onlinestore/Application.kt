package com.greenrobotdev.onlinestore

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.greenrobotdev.onlinestore.di.initKoin
import platform.UIKit.UIViewController
import androidx.compose.runtime.CompositionLocalProvider
import com.greenrobotdev.onlinestore.screen.home.HomeScreen

fun Main(): UIViewController = ComposeUIViewController {

    CompositionLocalProvider() {
        MaterialTheme {
            HomeScreen()
        }
    }

}

