package com.greenrobotdev.onlinestore

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.window.Application
import androidx.compose.ui.window.ComposeUIViewController
import platform.Foundation.NSHomeDirectory
import platform.UIKit.UIViewController

//fun Main(): UIViewController  = ComposeUIViewController {
//        App()
//    }


fun Main(): UIViewController = ComposeUIViewController {
    HomeScreen()
}

