package com.example.networktask8.presentation.user_screen

sealed class ContentSortState() {
    object AllUser : ContentSortState()
    object RandomUser : ContentSortState()
    object AllResource : ContentSortState()
    object CreateUser : ContentSortState()
}
