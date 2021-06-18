package com.ibrahim.dev.mercado_ibra.home.data.entities


import com.google.gson.annotations.SerializedName

data class Metadata(
    @SerializedName("campaign_id")
    val campaignId: String?,
    @SerializedName("promotion_id")
    val promotionId: String?,
    @SerializedName("promotion_type")
    val promotionType: String?
)