package com.example.networktask8.presentation.navigation

sealed class Screens(val route: String) {
    object Content : Screens(route = "content_screen")
    object CreateUser : Screens(route = "create_user")
}
