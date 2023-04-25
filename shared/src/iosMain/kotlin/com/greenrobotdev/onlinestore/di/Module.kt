package com.greenrobotdev.onlinestore.di

import com.greenrobotdev.onlinestore.data.factory.ApiService
import com.greenrobotdev.onlinestore.data.factory.DriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single { DriverFactory() }
    single { ApiService() }
}