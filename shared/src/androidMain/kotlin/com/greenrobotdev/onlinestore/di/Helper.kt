package com.greenrobotdev.onlinestore.di

import com.greenrobotdev.onlinestore.di.platformModule
import org.koin.core.context.GlobalContext.startKoin

fun initKoin(){
    startKoin {
        modules(platformModule())
    }
}