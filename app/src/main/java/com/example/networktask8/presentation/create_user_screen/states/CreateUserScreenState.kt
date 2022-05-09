package com.example.networktask8.presentation.create_user_screen.states

import com.example.networktask8.data.data_source.model.response.CreateUserResponse

data class CreateUserScreenState(
    val userIsCreated: CreateUserResponse? = null,
    val error:String? = null,
    val isLoading:Boolean = false
)
