package com.example.networktask8.presentation.content_screen.states

sealed class ContentSortState() {
    object AllUser : ContentSortState()
    object RandomUser : ContentSortState()
    object AllResource : ContentSortState()
}
