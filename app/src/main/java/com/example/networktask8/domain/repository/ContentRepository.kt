package com.example.networktask8.domain.repository

import com.example.networktask8.data.data_source.model.dto.CreateUserRequest
import com.example.networktask8.data.data_source.model.response.CreateUserResponse
import com.example.networktask8.domain.model.Resource
import com.example.networktask8.domain.model.User

interface ContentRepository {
    suspend fun getUserList(): List<User>?
    suspend fun getRandomUser(): User?
    suspend fun getResourceList(): List<Resource>?
    suspend fun createUser(createUserRequest: CreateUserRequest): CreateUserResponse?
}