package com.example.networktask8.domain.repository

import com.example.networktask8.domain.model.Resource
import com.example.networktask8.domain.model.User

interface ContentRepository {
    suspend fun getUserList(): List<User>?
    suspend fun getRandomUser(): User?
    suspend fun getResourceList(): List<Resource>?
}