package com.ibrahim.dev.mercado_ibra.splash.data.entities


import com.google.gson.annotations.SerializedName
import com.ibrahim.dev.mercado_ibra.splash.domain.models.CategoriesModel

data class CategoriesResponse(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?
) {
    fun toModel(): CategoriesModel {
        return CategoriesModel(id.orEmpty(), name.orEmpty())
    }

}

