package com.example.networktask8.data.data_source

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


fun provideRetrofitInstance(): Retrofit =
    Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://reqres.in/").build()

fun provideService(retrofit: Retrofit): UserService = retrofit.create(UserService::class.java)