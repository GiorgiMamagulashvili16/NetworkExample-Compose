package com.example.networktask8.presentation.content_screen.states

import com.example.networktask8.domain.model.Resource
import com.example.networktask8.domain.model.User

data class UserScreenState(
    val userDataFetched: List<User>? = null,
    val singleUserFetched: User? = null,
    val resourcesFetched: List<Resource>? = null,
    val error: String? = null,
    val isLoading: Boolean = false,
    val contentSortState: ContentSortState = ContentSortState.AllUser,
)