package com.ibrahim.dev.mercado_ibra.home.data.entities


import com.google.gson.annotations.SerializedName

data class Prices(
    @SerializedName("id")
    val id: String?,
    @SerializedName("presentation")
    val presentation: Presentation?,
    @SerializedName("prices")
    val prices: List<Price>?
)