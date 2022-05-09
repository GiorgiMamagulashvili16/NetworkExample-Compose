package com.example.networktask8.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.networktask8.presentation.content_screen.ContentScreen
import com.example.networktask8.presentation.create_user_screen.CreateUserScreen


@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Content.route,
    ) {
        composable(
            route = Screens.Content.route
        ) {
            ContentScreen(navController)
        }
        composable(
            route = Screens.CreateUser.route
        ){
            CreateUserScreen()
        }
    }
}