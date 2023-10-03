package com.example.navigationdrawertutorial

import androidx.compose.ui.graphics.vector.ImageVector

data class DrawerMenuItem(
    val id: String,
    val title: String,
    val icon: ImageVector,
    val description: String
)
