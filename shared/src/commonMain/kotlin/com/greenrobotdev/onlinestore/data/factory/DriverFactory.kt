package com.greenrobotdev.onlinestore.data.factory

import com.squareup.sqldelight.db.SqlDriver
import io.ktor.client.*

expect class DriverFactory {
    fun createDriver(): SqlDriver
}


expect class ApiService() {
    fun build(): HttpClient
}