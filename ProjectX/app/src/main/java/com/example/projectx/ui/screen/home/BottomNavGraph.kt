package com.example.projectx

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.projectx.ui.screen.home.SellerDashboardHome

@Composable
fun BottomNavGraph(navController: NavHostController){
    NavHost(
        navController= navController,
        startDestination = "home"
    ){
        composable(route = "home"){
            SellerDashboardHome()
        }
        composable(route = "chat"){
            SellerDashboardHome()
        }
        composable(route = "user"){
            SellerDashboardHome()
        }
        composable(route = "incognito"){
            SellerDashboardHome()
        }
        composable(route = "world"){
            SellerDashboardHome()
        }


    }
}