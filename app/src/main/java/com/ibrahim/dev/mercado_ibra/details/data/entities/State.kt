package com.ibrahim.dev.mercado_ibra.details.data.entities


import com.google.gson.annotations.SerializedName

data class State(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?
)