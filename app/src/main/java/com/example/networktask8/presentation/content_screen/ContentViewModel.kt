package com.example.networktask8.presentation.content_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.networktask8.domain.use_case.GetRandomUserUseCase
import com.example.networktask8.domain.use_case.GetResourcesUseCase
import com.example.networktask8.domain.use_case.GetUsersUseCase
import com.example.networktask8.presentation.content_screen.states.ContentSortState
import com.example.networktask8.presentation.content_screen.states.UserScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContentViewModel(
    private val getUsersUseCase: GetUsersUseCase,
    private val getRandomUser: GetRandomUserUseCase,
    private val getResourcesUseCase: GetResourcesUseCase,
) : ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {
            getContentEvent(ContentSortState.AllUser)
        }
    }

    private val _screenState = mutableStateOf(UserScreenState())
    val screenState: State<UserScreenState> = _screenState


    fun getContentEvent(event: ContentSortState) {
        when (event) {
            is ContentSortState.AllUser -> {
                getUserList()
            }
            is ContentSortState.AllResource -> {
                getResources()
            }
            is ContentSortState.RandomUser -> {
                getRandomUser()
            }
        }
    }



    private fun getResources() = viewModelScope.launch {
        _screenState.value = UserScreenState(isLoading = true)
        val result = getResourcesUseCase.execute()
        result?.let {
            _screenState.value = UserScreenState(
                resourcesFetched = it,
                contentSortState = ContentSortState.AllResource
            )
        }
    }

    private fun getRandomUser() = viewModelScope.launch {
        _screenState.value = UserScreenState(isLoading = true)
        val result = getRandomUser.execute()
        result?.let {
            _screenState.value = UserScreenState(
                singleUserFetched = it,
                contentSortState = ContentSortState.RandomUser
            )
        } ?: emitErrorState()
    }

    private fun getUserList() = viewModelScope.launch {
        _screenState.value = UserScreenState(isLoading = true)
        val result = getUsersUseCase.execute()
        result?.let {
            _screenState.value =
                UserScreenState(userDataFetched = it, contentSortState = ContentSortState.AllUser)
        } ?: emitErrorState()
    }

    private fun emitErrorState() = viewModelScope.launch(Dispatchers.IO) {
        _screenState.value = UserScreenState(error = "data is null")
    }
}