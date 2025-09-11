package com.mertyigit0.yugiohdeck.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mertyigit0.yugiohdeck.ui.detail.DetailScreen
import com.mertyigit0.yugiohdeck.ui.home.HomeScreen
import com.mertyigit0.yugiohdeck.ui.mydeck.MyDeckScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                onCardClick = { cardId ->
                    navController.navigate("detail/$cardId")
                },
                onMyDeckClick = {
                    navController.navigate("mydeck")
                }
            )
        }
        composable(
            route = "detail/{cardId}",
            arguments = listOf(navArgument("cardId") { type = NavType.IntType })
        ) {
            DetailScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
        composable("mydeck") {
            MyDeckScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
