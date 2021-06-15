package com.ibrahim.dev.mercado_ibra.home.data.entities


import com.google.gson.annotations.SerializedName

data class Installments(
    @SerializedName("amount")
    val amount: Double?,
    @SerializedName("currency_id")
    val currencyId: String?,
    @SerializedName("quantity")
    val quantity: Int?,
    @SerializedName("rate")
    val rate: Int?
)