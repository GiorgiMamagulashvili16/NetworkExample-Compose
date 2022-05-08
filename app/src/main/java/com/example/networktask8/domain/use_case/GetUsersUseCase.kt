package com.example.networktask8.domain.use_case

import com.example.networktask8.domain.model.User
import com.example.networktask8.domain.repository.ContentRepository

class GetUsersUseCase(private val contentRepository: ContentRepository) {
     suspend fun execute(): List<User>? {
        return contentRepository.getUserList()
    }
}