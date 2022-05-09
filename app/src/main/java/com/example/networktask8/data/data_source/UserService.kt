package com.example.networktask8.data.data_source

import com.example.networktask8.data.data_source.model.dto.CreateUserRequest
import com.example.networktask8.data.data_source.model.dto.ResourceDto
import com.example.networktask8.data.data_source.model.dto.UserDto
import com.example.networktask8.data.data_source.model.response.CreateUserResponse
import com.example.networktask8.data.data_source.model.response.SingleUserResponse
import com.example.networktask8.data.data_source.model.response.UserResponse
import retrofit2.Response
import retrofit2.http.*

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

    @POST("/api/users")
    suspend fun createUser(
        @Body createUserRequest: CreateUserRequest
    ): Response<CreateUserResponse>
}