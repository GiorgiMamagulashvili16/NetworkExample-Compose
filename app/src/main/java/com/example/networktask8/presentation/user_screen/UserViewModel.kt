package com.example.networktask8.presentation.user_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.networktask8.data.data_source.RetrofitInstance
import com.example.networktask8.data.repository.ContentRepositoryImpl
import com.example.networktask8.domain.repository.ContentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val contentRepository: ContentRepository by lazy {
        ContentRepositoryImpl(
            RetrofitInstance.userService
        )
    }

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
            is ContentSortState.CreateUser -> {

            }
        }
    }

    private fun getResources() = viewModelScope.launch {
        _screenState.value = UserScreenState(isLoading = true)
        val result = contentRepository.getResourceList()
        result?.let {
            _screenState.value = UserScreenState(
                resourcesFetched = it,
                contentSortState = ContentSortState.AllResource
            )
        }
    }

    private fun getRandomUser() = viewModelScope.launch {
        _screenState.value = UserScreenState(isLoading = true)
        val result = contentRepository.getRandomUser()
        result?.let {
            _screenState.value = UserScreenState(
                singleUserFetched = it,
                contentSortState = ContentSortState.RandomUser
            )
        } ?: emitErrorState()
    }

    private fun getUserList() = viewModelScope.launch {
        _screenState.value = UserScreenState(isLoading = true)
        val result = contentRepository.getUserList()
        result?.let {
            _screenState.value =
                UserScreenState(userDataFetched = it, contentSortState = ContentSortState.AllUser)
        } ?: emitErrorState()
    }

    private fun emitErrorState() = viewModelScope.launch(Dispatchers.IO) {
        _screenState.value = UserScreenState(error = "data is null")
    }
}