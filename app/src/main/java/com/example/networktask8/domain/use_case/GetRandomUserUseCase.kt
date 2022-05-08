package com.example.networktask8.domain.use_case

import com.example.networktask8.domain.model.User
import com.example.networktask8.domain.repository.ContentRepository

class GetRandomUserUseCase(private val contentRepository: ContentRepository) {

    suspend fun execute(): User? {
        return contentRepository.getRandomUser()
    }
}