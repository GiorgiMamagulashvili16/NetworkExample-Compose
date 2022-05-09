package com.example.networktask8.presentation.create_user_screen.states

sealed class CreateUserStates {
    class EnteredUserName(val value: String) : CreateUserStates()
    class EnteredUserJob(val value: String) : CreateUserStates()
    object CreateUser : CreateUserStates()
}
