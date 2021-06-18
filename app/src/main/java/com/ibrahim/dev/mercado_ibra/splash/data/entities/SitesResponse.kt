package com.ibrahim.dev.mercado_ibra.splash.data.entities


import com.google.gson.annotations.SerializedName
import com.ibrahim.dev.mercado_ibra.splash.domain.models.SitesModel

data class SitesResponse(
    @SerializedName("default_currency_id")
    val defaultCurrencyId: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?
) {
    fun toModel(): SitesModel = SitesModel(
        defaultCurrencyId.orEmpty(),
        id.orEmpty(),
        name.orEmpty()
    )
}