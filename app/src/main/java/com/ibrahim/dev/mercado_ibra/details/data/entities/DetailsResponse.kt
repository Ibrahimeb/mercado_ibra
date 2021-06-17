package com.ibrahim.dev.mercado_ibra.details.data.entities


import com.google.gson.annotations.SerializedName
import com.ibrahim.dev.mercado_ibra.commons.utils.orFalse
import com.ibrahim.dev.mercado_ibra.commons.utils.orZero
import com.ibrahim.dev.mercado_ibra.details.domain.models.ProductDetailsModel

data class DetailsResponse(
    @SerializedName("body")
    val body: Body?,
    @SerializedName("code")
    val code: Int?
) {
    fun toModel(): ProductDetailsModel = ProductDetailsModel(
        body?.acceptsMercadopago.orFalse(),
        body?.availableQuantity.orZero(),
        body?.sellerAddress?.city?.name.orEmpty(),
        body?.currencyId.orEmpty(),
        body?.pictures?.get(0)?.url.orEmpty(),
        body?.initialQuantity.orZero(),
        body?.price ?: 0.0,
        body?.soldQuantity.orZero(),
        body?.sellerAddress?.state?.name.orEmpty(),
        body?.title.orEmpty(),
        body?.warranty.orEmpty(),
        body?.shipping?.freeShipping.orFalse(),
    )
}