package com.ibrahim.dev.mercado_ibra.home.data.entities


import com.google.gson.annotations.SerializedName

data class Sort(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?
)