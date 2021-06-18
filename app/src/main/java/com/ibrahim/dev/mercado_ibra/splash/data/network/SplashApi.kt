package com.ibrahim.dev.mercado_ibra.splash.data.network

import com.ibrahim.dev.mercado_ibra.splash.data.entities.SitesResponse
import retrofit2.http.GET

interface SplashApi {

    @GET("sites")
    suspend fun getSites():List<SitesResponse>
}