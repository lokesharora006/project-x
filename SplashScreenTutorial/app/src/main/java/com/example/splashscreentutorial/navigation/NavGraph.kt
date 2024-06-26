package com.example.splashscreentutorial.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.splashscreentutorial.AnimatedSplashScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController , startDestination = Screen.Splash.route ){
        composable(Screen.Splash.route){
            AnimatedSplashScreen(navController )
        }
        composable(Screen.Home.route){
            Box(modifier=Modifier.fillMaxSize())
        }
    }
}