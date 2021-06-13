package com.ibrahim.dev.mercado_ibra.splash.data.entities


import com.google.gson.annotations.SerializedName
import com.ibrahim.dev.mercado_ibra.splash.domain.models.CategoryModel

data class CategoryResponse(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?
) {
    fun toModel(): CategoryModel {
        return CategoryModel(id.orEmpty(), name.orEmpty())
    }

}

