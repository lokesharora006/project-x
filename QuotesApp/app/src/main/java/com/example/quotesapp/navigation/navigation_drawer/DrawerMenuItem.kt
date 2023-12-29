package com.example.quotesapp.navigation.navigation_drawer

import androidx.compose.ui.graphics.vector.ImageVector

data class DrawerMenuItem(
    val id: String,
    val title: String,
    val icon: ImageVector,
    val description: String
)