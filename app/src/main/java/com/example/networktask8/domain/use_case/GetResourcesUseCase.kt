package com.example.networktask8.domain.use_case

import com.example.networktask8.domain.model.Resource
import com.example.networktask8.domain.repository.ContentRepository

class GetResourcesUseCase(private val contentRepository: ContentRepository) {

    suspend fun execute(): List<Resource>? {
        return contentRepository.getResourceList()
    }
}