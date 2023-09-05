package com.example.myjcmovieapp.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myjcmovieapp.screens.home.HomeScreen
import com.example.myjcmovieapp.screens.details.DetailsScreen

@Composable
fun MovieNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MovieScreens.HomeScreen.name) {

        composable(MovieScreens.HomeScreen.name) {
            HomeScreen(navController = navController)
        }

        composable(
            route = MovieScreens.DetailsScreen.name + "/{movie}",
            arguments = listOf(
                navArgument(name = "movie") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            DetailsScreen(
                navController = navController,
                movieId = backStackEntry.arguments?.getString("movie")
            )
        }
    }
}