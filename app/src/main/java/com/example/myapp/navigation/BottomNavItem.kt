package com.example.myapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val title: String, val icon: ImageVector) {
    object Home : BottomNavItem("home", "Home", Icons.Filled.Home)
    object Profile : BottomNavItem("profile", "Profile", Icons.Filled.Person)
    object Biblioteca : BottomNavItem("biblioteca", "Biblioteca", Icons.Filled.List)
}