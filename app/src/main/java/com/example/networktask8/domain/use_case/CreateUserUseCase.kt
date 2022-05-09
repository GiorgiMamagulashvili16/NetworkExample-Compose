package com.example.networktask8.domain.use_case

import com.example.networktask8.data.data_source.model.dto.CreateUserRequest
import com.example.networktask8.data.data_source.model.response.CreateUserResponse
import com.example.networktask8.domain.repository.ContentRepository

class CreateUserUseCase(private val contentRepository: ContentRepository) {
    suspend fun execute(createUserRequest: CreateUserRequest): CreateUserResponse? {
        return contentRepository.createUser(createUserRequest)
    }
}