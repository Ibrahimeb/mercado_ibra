package com.ibrahim.dev.mercado_ibra.home.data.network

import com.ibrahim.dev.mercado_ibra.home.data.entities.ProductListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApi {

    @GET("search")
    suspend fun getProductByCategories(@Query("category") category: String): ProductListResponse

    @GET("search")
    suspend fun getProductByQuery(@Query("q") query: String): ProductListResponse
}