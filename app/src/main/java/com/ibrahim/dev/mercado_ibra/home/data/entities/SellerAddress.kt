package com.ibrahim.dev.mercado_ibra.home.data.entities


import com.google.gson.annotations.SerializedName

data class SellerAddress(
    @SerializedName("address_line")
    val addressLine: String?,
    @SerializedName("city")
    val city: City?,
    @SerializedName("comment")
    val comment: String?,
    @SerializedName("country")
    val country: Country?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("latitude")
    val latitude: String?,
    @SerializedName("longitude")
    val longitude: String?,
    @SerializedName("state")
    val state: State?,
    @SerializedName("zip_code")
    val zipCode: String?
)