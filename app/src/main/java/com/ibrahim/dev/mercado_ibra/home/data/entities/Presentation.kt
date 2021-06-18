package com.ibrahim.dev.mercado_ibra.home.data.entities


import com.google.gson.annotations.SerializedName

data class Presentation(
    @SerializedName("display_currency")
    val displayCurrency: String?
)