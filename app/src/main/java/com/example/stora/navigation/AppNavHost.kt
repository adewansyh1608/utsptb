package com.example.stora.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.stora.screens.AddItemScreen
import com.example.stora.screens.DetailScreen
import com.example.stora.screens.HomeScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.HOME_SCREEN
    ) {
        // Rute untuk Layar Utama (Home)
        composable(
            route = Routes.HOME_SCREEN,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(500)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(500)
                )
            }
        ) {
            HomeScreen(navController = navController)
        }

        // Rute untuk Layar Detail
        composable(
            route = Routes.DETAIL_SCREEN,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(500)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(500)
                )
            }
        ) { backStackEntry ->
            // Mengambil itemId dari argumen rute
            val itemId = backStackEntry.arguments?.getString("itemId")
            DetailScreen(navController = navController, itemId = itemId)
        }

        // Rute untuk Layar Tambah Item
        composable(
            route = Routes.ADD_ITEM_SCREEN,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Up,
                    animationSpec = tween(500)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Down,
                    animationSpec = tween(500)
                )
            }
        ) {
            AddItemScreen(navController = navController)
        }
    }
}