package com.ibrahim.dev.mercado_ibra.home.domain.models

data class ProductListModel(
    val id: String,
    val title: String,
    val urlImage: String,
    val price: Double?,
    val currency: String,
    val availableQuantity: Int?,
    val sellerQuantity: Int?,
    val city: String,
    val hasMercadoPago: Boolean,
    val hasFreeShipping: Boolean
)


