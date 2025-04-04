package com.example.myapp.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapp.screens.BibliotecaScreen
import com.example.myapp.screens.DetailsScreen
import com.example.myapp.screens.HomeScreen
import com.example.myapp.screens.ProfileScreen
import com.example.myapp.viewmodel.BookViewModel

//navController: NavHostController,
@Composable
fun NavGraph(bookViewModel: BookViewModel) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController, bookViewModel) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            NavHost(
                navController = navController,
                startDestination = BottomNavItem.Home.route
            ) {
                composable(BottomNavItem.Home.route) {
                    HomeScreen(bookViewModel, navController)
                }
                composable(BottomNavItem.Profile.route) {
                    ProfileScreen(navController)
                }
                composable(BottomNavItem.Biblioteca.route) {
                    BibliotecaScreen(bookViewModel, navController)
                }
                composable(
                    route = "details/{bookId}",
                    arguments = listOf(navArgument("bookId") { type = NavType.IntType })
                ) { backStackEntry ->
                    val bookId = backStackEntry.arguments?.getInt("bookId")
                    requireNotNull(bookId) { "bookId parameter is required!" }
                    DetailsScreen(bookViewModel,navController, bookId)
                }
            }
        }
    }
}