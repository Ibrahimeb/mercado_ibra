package com.ibrahim.dev.mercado_ibra.home.data.network

import com.ibrahim.dev.mercado_ibra.home.data.entities.ProductListResponse
import com.ibrahim.dev.mercado_ibra.home.data.entities.category.CategoriesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HomeApi {

    @GET("{siteId}/categories")
    suspend fun getCategories(@Path("siteId") sitesId: String): List<CategoriesResponse>

    @GET("{siteId}/search")
    suspend fun getProductByCategories(
        @Path("siteId") sitesCode: String,
        @Query("category") category: String,
    ): ProductListResponse

    @GET("{siteId}/search")
    suspend fun getProductByQuery(
        @Path("siteId") sitesCode: String,
        @Query("q") query: String,
    ): ProductListResponse
}