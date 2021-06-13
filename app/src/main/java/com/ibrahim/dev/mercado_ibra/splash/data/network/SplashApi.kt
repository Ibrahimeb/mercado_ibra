package com.ibrahim.dev.mercado_ibra.splash.data.network

import com.ibrahim.dev.mercado_ibra.splash.data.entities.CategoryResponse
import retrofit2.http.GET

interface SplashApi {

    @GET("categories")
    suspend fun getCategories(): List<CategoryResponse>
}