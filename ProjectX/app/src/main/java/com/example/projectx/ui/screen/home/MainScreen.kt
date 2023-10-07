package com.example.projectx

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.projectx.ui.screen.home.BottomBarItem


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(){
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                items = listOf(
                    BottomBarItem(
                        title = "Home",
                        route = "home",
                        icon = R.drawable.grid_icon
                    ),
                    BottomBarItem(
                        title = "Chat",
                        route = "chat",
                        icon = R.drawable.chat_icon
                    ),
                    BottomBarItem(
                        title = "Incognito",
                        route = "incognito",
                        icon = R.drawable.incognito_outline
                    ),
                    BottomBarItem(
                        title = "World",
                        route = "world",
                        icon = R.drawable.user
                    ),
                    BottomBarItem(
                        title = "User",
                        route = "user",
                        icon = R.drawable.user
                    )
                ),
                navController =navController,
                onItemClick = {
                    navController.navigate(it.route)
                }
            )
        }
    ){
        BottomNavGraph(navController)
    }
}


@Composable
fun BottomNavigationBar(
    items: List<BottomBarItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomBarItem) -> Unit
){
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = modifier
            .height(56.dp)
            .clip(shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
        backgroundColor = Color(0xFFFFFFFF),
        elevation = 20.dp
    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected ,
                onClick = { onItemClick(item) },
                selectedContentColor = Color(0xffff7622),
                unselectedContentColor = Color.Gray,
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = "icon",
                        modifier = Modifier
                            .size(24.dp)
                    )
                } ,

            )
                
        }
    }
}
