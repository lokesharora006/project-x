package com.example.iostesting

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform