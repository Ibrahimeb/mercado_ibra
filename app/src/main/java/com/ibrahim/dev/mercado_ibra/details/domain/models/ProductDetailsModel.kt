package com.ibrahim.dev.mercado_ibra.details.domain.models

data class ProductDetailsModel(
    val acceptsMercadoPago: Boolean,
    val availableQuantity: Int,
    val city: String,
    val currencyId: String,
    val imageUrl: String,
    val initialQuantity: Int,
    val price: Double,
    val soldQuantity: Int,
    val state: String,
    val title: String,
    val warranty: String,
    val freeShipping: Boolean,
)