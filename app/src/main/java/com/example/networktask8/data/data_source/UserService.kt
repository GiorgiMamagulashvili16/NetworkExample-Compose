package com.example.networktask8.data.data_source

import com.example.networktask8.data.data_source.dto.ResourceDto
import com.example.networktask8.data.data_source.dto.SingleUserResponse
import com.example.networktask8.data.data_source.dto.UserDto
import com.example.networktask8.data.data_source.dto.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {

    @GET("api/users?")
    suspend fun getUserList(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 12
    ): Response<UserResponse<UserDto>>

    @GET("/api/users/{id}")
    suspend fun getRandomUser(
        @Path("id") id: Int
    ): Response<SingleUserResponse>

    @GET("/api/unknown")
    suspend fun getResources(): Response<UserResponse<ResourceDto>>
}