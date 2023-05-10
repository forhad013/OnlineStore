package com.greenrobotdev.onlinestore.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.greenrobotdev.onlinestore.screen.home.App
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.defaultComponentContext
import io.github.xxfast.decompose.LocalComponentContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        val rootComponentContext: DefaultComponentContext = defaultComponentContext()

        setContent {
            CompositionLocalProvider(LocalComponentContext provides rootComponentContext) {
                MaterialTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        App()
                    }
                }
            }

            //      val rootComponentContext: DefaultComponentContext = defaultComponentContext()
//        CompositionLocalProvider(LocalComponentContext provides rootComponentContext) {
//            setContent {
//                MaterialTheme {
//                    Surface(
//                        modifier = Modifier.fillMaxSize(),
//                        color = MaterialTheme.colorScheme.background
//                    ) {
//                       App()
//                    }
//                }
//            }
//
//
//        }


        }
    }
}

 