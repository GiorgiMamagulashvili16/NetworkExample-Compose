package com.example.networktask8.data.data_source.model.dto

import com.example.networktask8.domain.model.User
import com.google.gson.annotations.SerializedName

data class UserDto(
    val avatar: String?,
    val email: String? = null,
    @SerializedName("first_name")
    val firstName: String,
    val id: Int,
    @SerializedName("last_name")
    val lastName: String
) {
    fun toUser(): User {
        return User(
            id = this.id,
            avatar = this.avatar,
            firstName = this.firstName,
            lastName = this.lastName
        )
    }
}