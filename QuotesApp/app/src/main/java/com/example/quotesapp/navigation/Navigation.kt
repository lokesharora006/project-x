package com.example.quotesapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.quotesapp.ui.screens.QuoteScreen
import com.example.quotesapp.ui.screens.QuoteViewModel
import com.example.quotesapp.ui.screens.SplashScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController,
        startDestination = Screen.Splash.route){
        composable(Screen.Splash.route){
            SplashScreen(navController)
        }
        composable(Screen.Home.route){

            val quoteViewModel: QuoteViewModel = viewModel(factory = QuoteViewModel.Factory )

            QuoteScreen(quoteUiState = quoteViewModel.quoteUiState, retryAction = quoteViewModel::getQuotes  )
        }
    }
}