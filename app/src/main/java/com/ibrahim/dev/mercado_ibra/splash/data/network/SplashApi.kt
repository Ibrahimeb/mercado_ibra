package com.ibrahim.dev.mercado_ibra.splash.data.network

import com.ibrahim.dev.mercado_ibra.splash.data.entities.CategoriesResponse
import retrofit2.http.GET

interface SplashApi {

    @GET("categories")
    suspend fun getCategories(): List<CategoriesResponse>
}