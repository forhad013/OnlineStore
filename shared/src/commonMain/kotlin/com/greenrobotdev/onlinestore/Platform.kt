package com.greenrobotdev.onlinestore

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform