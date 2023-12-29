package com.example.navigationdrawertutorial

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.navigationdrawertutorial.ui.theme.NavigationDrawerTutorialTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationDrawerTutorialTheme {
                // A surface container using the 'background' color from the theme
                val scaffoldState = rememberScaffoldState()
                val scope = rememberCoroutineScope()
                Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = {
                             AppBar(
                                 onNavigationIconClick = {
                                    scope.launch { scaffoldState.drawerState.open() }
                                 }
                             )
                    },
                    drawerContent = {
                        DrawerHeader()
                        DrawerBody(
                            items = listOf(
                                DrawerMenuItem(
                                    id = "home",
                                    title = "Home",
                                    icon = Icons.Default.Home,
                                    description = "Go to Home"
                                ),
                                DrawerMenuItem(
                                    id = "settings",
                                    title = "Settings",
                                    icon = Icons.Default.Settings,
                                    description = "Go to Settings"
                                ),
                                DrawerMenuItem(
                                    id = "Help",
                                    title = "Help",
                                    icon = Icons.Default.Info,
                                    description = "Go to Help"
                                )
                            ) ,
                            onItemClick = {
                                println("Clicked on ${it.title}")
                            } )
                    }
                ) {

                }
            }
        }
    }
}
