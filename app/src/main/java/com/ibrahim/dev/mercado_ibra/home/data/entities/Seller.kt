package com.ibrahim.dev.mercado_ibra.home.data.entities


import com.google.gson.annotations.SerializedName

data class Seller(
    @SerializedName("car_dealer")
    val carDealer: Boolean?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("permalink")
    val permalink: Any?,
    @SerializedName("real_estate_agency")
    val realEstateAgency: Boolean?,
    @SerializedName("registration_date")
    val registrationDate: Any?,
    @SerializedName("tags")
    val tags: Any?
)