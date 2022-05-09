package com.example.networktask8.presentation.create_user_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.networktask8.data.data_source.model.dto.CreateUserRequest
import com.example.networktask8.domain.use_case.CreateUserUseCase
import com.example.networktask8.presentation.create_user_screen.states.CreateUserScreenState
import com.example.networktask8.presentation.create_user_screen.states.CreateUserStates
import kotlinx.coroutines.launch

class CreateUserViewModel(
    private val createUserUseCase: CreateUserUseCase
) : ViewModel() {
    private val _userName = mutableStateOf("")
    val userName: State<String> = _userName

    private val _job = mutableStateOf("")
    val job: State<String> = _job

    private val _screenState = mutableStateOf(CreateUserScreenState())
    val screenState: State<CreateUserScreenState> = _screenState

    fun createUserEvent(state: CreateUserStates) {
        when (state) {
            is CreateUserStates.EnteredUserName -> {
                _userName.value = state.value
            }
            is CreateUserStates.EnteredUserJob -> {
                _job.value = state.value
            }
            is CreateUserStates.CreateUser -> {
                createUser()
            }
        }
    }

    private fun createUser() = viewModelScope.launch {
        _screenState.value = CreateUserScreenState(isLoading = true)
        val request = CreateUserRequest(
            job = job.value,
            name = userName.value
        )
        val response = createUserUseCase.execute(request)
        if (response != null) {
            _screenState.value = CreateUserScreenState(
                userIsCreated = response
            )
        } else {
            _screenState.value = CreateUserScreenState(
                error = "user create failed!"
            )
        }
    }
}