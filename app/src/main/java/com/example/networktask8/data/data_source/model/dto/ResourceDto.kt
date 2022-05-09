package com.example.networktask8.data.data_source.model.dto

import com.example.networktask8.domain.model.Resource
import com.google.gson.annotations.SerializedName

data class ResourceDto(
    val color: String,
    val id: Int,
    val name: String,
    @SerializedName("pantone_value")
    val pantoneValue: String,
    val year: Int
) {
    fun toResource(): Resource {
        return Resource(
            color = this.color,
            id = this.id,
            name = this.name,
            year = this.year
        )
    }
}