package com.ibrahim.dev.mercado_ibra.home.data.entities


import com.google.gson.annotations.SerializedName
import com.ibrahim.dev.mercado_ibra.commons.utils.orFalse
import com.ibrahim.dev.mercado_ibra.home.domain.models.ProductListModel

data class ProductListResponse(
    @SerializedName("available_filters")
    val availableFilters: List<AvailableFilter>?,
    @SerializedName("available_sorts")
    val availableSorts: List<AvailableSort>?,
    @SerializedName("filters")
    val filters: List<Filter>?,
    @SerializedName("paging")
    val paging: Paging?,
    @SerializedName("query")
    val query: String?,
    @SerializedName("related_results")
    val relatedResults: List<Any>?,
    @SerializedName("results")
    val results: List<Result>?,
    @SerializedName("secondary_results")
    val secondaryResults: List<Any>?,
    @SerializedName("site_id")
    val siteId: String?,
    @SerializedName("sort")
    val sort: Sort?
) {
    fun toModel(): List<ProductListModel> {
        return results?.map { item ->
            ProductListModel(
                item.id.orEmpty(),
                item.title.orEmpty(),
                item.thumbnail.orEmpty(),
                item.price,
                item.availableQuantity,
                item.soldQuantity,
                item.address?.stateName.orEmpty(),
                item.acceptsMercadopago.orFalse(),
                item.shipping?.freeShipping.orFalse()
            )
        } ?: emptyList()
    }
}
