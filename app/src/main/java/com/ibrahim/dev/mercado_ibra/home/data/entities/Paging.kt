package com.ibrahim.dev.mercado_ibra.home.data.entities


import com.google.gson.annotations.SerializedName

data class Paging(
    @SerializedName("limit")
    val limit: Int?,
    @SerializedName("offset")
    val offset: Int?,
    @SerializedName("primary_results")
    val primaryResults: Int?,
    @SerializedName("total")
    val total: Int?
)