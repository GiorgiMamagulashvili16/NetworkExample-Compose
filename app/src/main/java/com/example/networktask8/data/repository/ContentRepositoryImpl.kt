package com.example.networktask8.data.repository

import com.example.networktask8.data.data_source.UserService
import com.example.networktask8.domain.model.Resource
import com.example.networktask8.domain.model.User
import com.example.networktask8.domain.repository.ContentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ContentRepositoryImpl(private val userService: UserService) : ContentRepository {

    override suspend fun getUserList(): List<User>? = withContext(Dispatchers.IO) {
        val response = userService.getUserList()
        return@withContext if (response.isSuccessful) {
            response.body()?.data?.map { it.toUser() }
        } else {
            null
        }
    }

    override suspend fun getRandomUser(): User? {
        val response = userService.getRandomUser(id = (7..12).random())
        return if (response.isSuccessful) {
            response.body()?.data?.toUser()
        } else {
            null
        }
    }

    override suspend fun getResourceList(): List<Resource>? {
        val response = userService.getResources()
        return if (response.isSuccessful) {
            response.body()?.data?.map { it.toResource() }
        } else {
            null
        }
    }
}