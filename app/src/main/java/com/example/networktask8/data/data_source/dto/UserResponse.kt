package com.example.networktask8.data.data_source.dto

import com.google.gson.annotations.SerializedName

data class UserResponse<T>(
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    val total: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    val data: List<T>
)