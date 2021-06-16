package com.ibrahim.dev.mercado_ibra.details.data.network

import com.ibrahim.dev.mercado_ibra.details.data.entities.DetailsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductDetailApi {

    @GET("items")
    suspend fun getItem(@Query("ids") ids: String): List<DetailsResponse>
}