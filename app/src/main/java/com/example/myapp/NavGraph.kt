package com.example.myapp
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapp.screens.DetailsScreen
import com.example.myapp.screens.HomeScreen
import com.example.myapp.screens.ProfileScreen



@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable(
            route = "details/{bookId}", // Route now contains a placeholder for the bookId
            arguments = listOf(navArgument("bookId") { type = NavType.StringType }) // Defines the argument
        ) { backStackEntry ->
            val bookId = backStackEntry.arguments?.getString("bookId")
            requireNotNull(bookId) { "bookId parameter is required!" }
            DetailsScreen(navController, bookId)
        }
        composable("profile") { ProfileScreen(navController) } // Pass navController here
        //composable("profile") { ProfileScreen() }
    }
}