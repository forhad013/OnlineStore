package com.greenrobotdev.onlinestore.data.factory

import com.greenrobotdev.onlinestore.shared.cache.StoreDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import io.ktor.client.*
import io.ktor.client.engine.darwin.*
import io.ktor.client.plugins.logging.*

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(StoreDatabase.Schema, "StoreDatabase.db")
    }
}

actual class ApiService {
    actual fun build(): HttpClient {
        return HttpClient(Darwin) {
            engine {
                configureRequest {
                    setAllowsCellularAccess(true)
                }
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }
    }
}

