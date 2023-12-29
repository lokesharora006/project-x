package com.example.quotesapp.navigation

sealed class Screen (val route: String){
    object Splash: Screen("Splash Screen")
    object Home: Screen("Home")

}