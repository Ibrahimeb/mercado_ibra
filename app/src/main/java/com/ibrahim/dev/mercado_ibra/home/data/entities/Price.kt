package com.ibrahim.dev.mercado_ibra.home.data.entities


import com.google.gson.annotations.SerializedName

data class Price(
    @SerializedName("amount")
    val amount: Int?,
    @SerializedName("conditions")
    val conditions: Conditions?,
    @SerializedName("currency_id")
    val currencyId: String?,
    @SerializedName("exchange_rate_context")
    val exchangeRateContext: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("last_updated")
    val lastUpdated: String?,
    @SerializedName("metadata")
    val metadata: Metadata?,
    @SerializedName("regular_amount")
    val regularAmount: Any?,
    @SerializedName("type")
    val type: String?
)