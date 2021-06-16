package com.ibrahim.dev.mercado_ibra.details.data.entities


import com.google.gson.annotations.SerializedName

data class Shipping(
    @SerializedName("dimensions")
    val dimensions: Any?,
    @SerializedName("free_shipping")
    val freeShipping: Boolean?,
    @SerializedName("local_pick_up")
    val localPickUp: Boolean?,
    @SerializedName("logistic_type")
    val logisticType: String?,
    @SerializedName("methods")
    val methods: List<Any>?,
    @SerializedName("mode")
    val mode: String?,
    @SerializedName("store_pick_up")
    val storePickUp: Boolean?,
    @SerializedName("tags")
    val tags: List<String>?
)