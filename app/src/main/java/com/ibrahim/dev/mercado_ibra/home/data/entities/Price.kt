package com.ibrahim.dev.mercado_ibra.home.data.entities


import com.google.gson.annotations.SerializedName

data class Price(
    @SerializedName("amount")
    val amount: Double?,
    @SerializedName("currency_id")
    val currencyId: String?,
)