package com.ibrahim.dev.mercado_ibra.details.data.entities


import com.google.gson.annotations.SerializedName

data class SearchLocation(
    @SerializedName("city")
    val city: CityX?,
    @SerializedName("neighborhood")
    val neighborhood: Neighborhood?,
    @SerializedName("state")
    val state: State?
)