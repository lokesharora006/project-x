package com.example.quotesapp

import android.app.Application
import com.example.quotesapp.data.AppContainer
import com.example.quotesapp.data.DefaultAppContainer

class QuotesApplication : Application(){
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer("6k3gbrkWc0NaeZGmQQnfHQ==rTOfQE2JyfqWP4mq")
    }
}