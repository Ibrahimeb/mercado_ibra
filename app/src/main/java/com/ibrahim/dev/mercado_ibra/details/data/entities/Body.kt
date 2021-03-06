package com.ibrahim.dev.mercado_ibra.details.data.entities

import com.google.gson.annotations.SerializedName

data class Body(
    @SerializedName("accepts_mercadopago")
    val acceptsMercadopago: Boolean?,
    @SerializedName("attributes")
    val attributes: List<Attribute>?,
    @SerializedName("automatic_relist")
    val automaticRelist: Boolean?,
    @SerializedName("available_quantity")
    val availableQuantity: Int?,
    @SerializedName("base_price")
    val basePrice: Double?,
    @SerializedName("buying_mode")
    val buyingMode: String?,
    @SerializedName("catalog_listing")
    val catalogListing: Boolean?,
    @SerializedName("category_id")
    val categoryId: String?,
    @SerializedName("channels")
    val channels: List<String>?,
    @SerializedName("condition")
    val condition: String?,
    @SerializedName("currency_id")
    val currencyId: String?,
    @SerializedName("date_created")
    val dateCreated: String?,
    @SerializedName("descriptions")
    val descriptions: List<Description>?,
    @SerializedName("domain_id")
    val domainId: String?,
    @SerializedName("health")
    val health: Double?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("initial_quantity")
    val initialQuantity: Int?,
    @SerializedName("international_delivery_mode")
    val internationalDeliveryMode: String?,
    @SerializedName("last_updated")
    val lastUpdated: String?,
    @SerializedName("listing_source")
    val listingSource: String?,
    @SerializedName("listing_type_id")
    val listingTypeId: String?,
    @SerializedName("location")
    val location: Location?,
    @SerializedName("permalink")
    val permalink: String?,
    @SerializedName("pictures")
    val pictures: List<Picture>?,
    @SerializedName("price")
    val price: Double?,
    @SerializedName("sale_terms")
    val saleTerms: List<SaleTerm>?,
    @SerializedName("secure_thumbnail")
    val secureThumbnail: String?,
    @SerializedName("seller_address")
    val sellerAddress: SellerAddress?,
    @SerializedName("seller_contact")
    val sellerContact: Any?,
    @SerializedName("seller_id")
    val sellerId: Int?,
    @SerializedName("shipping")
    val shipping: Shipping?,
    @SerializedName("site_id")
    val siteId: String?,
    @SerializedName("sold_quantity")
    val soldQuantity: Int?,
    @SerializedName("start_time")
    val startTime: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("stop_time")
    val stopTime: String?,
    @SerializedName("thumbnail")
    val thumbnail: String?,
    @SerializedName("thumbnail_id")
    val thumbnailId: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("warranty")
    val warranty: String?
)