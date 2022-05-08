package com.example.networktask8.data.data_source

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://reqres.in/"
    private val retrofitInstance by lazy {
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL)
            .build()
    }
    val userService: UserService by lazy {
        return@lazy retrofitInstance.create(UserService::class.java)
    }
}