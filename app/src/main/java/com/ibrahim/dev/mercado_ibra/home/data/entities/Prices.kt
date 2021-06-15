package com.ibrahim.dev.mercado_ibra.home.data.entities


import com.google.gson.annotations.SerializedName

data class Prices(
    @SerializedName("id")
    val id: String?,
    @SerializedName("payment_method_prices")
    val paymentMethodPrices: List<Any>?,
    @SerializedName("presentation")
    val presentation: Presentation?,
    @SerializedName("prices")
    val prices: List<Price>?,
    @SerializedName("purchase_discounts")
    val purchaseDiscounts: List<Any>?,
    @SerializedName("reference_prices")
    val referencePrices: List<Any>?
)