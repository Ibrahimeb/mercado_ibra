package com.ibrahim.dev.mercado_ibra.splash.data.di

import com.ibrahim.dev.mercado_ibra.app.di.UrlWithOutSites
import com.ibrahim.dev.mercado_ibra.splash.data.network.SplashApi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class SplashApiModule {


    @Provides
    fun providerApi(@UrlWithOutSites retrofit: Retrofit): SplashApi {
        return retrofit.create(SplashApi::class.java)
    }
}