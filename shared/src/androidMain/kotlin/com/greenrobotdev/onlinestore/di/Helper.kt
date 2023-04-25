package com.example.templatekmm.di

import com.greenrobotdev.onlinestore.di.platformModule
import org.koin.core.context.GlobalContext.startKoin

//class GreetingHelper : KoinComponent {
//    private val greeting : Greeting by inject()
//    fun greet() : String = greeting.greet()
//}

fun initKoin(){
    startKoin {
        modules(platformModule())
    }
}