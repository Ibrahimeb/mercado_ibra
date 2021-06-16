package com.ibrahim.dev.mercado_ibra.details.data.di

import com.ibrahim.dev.mercado_ibra.app.di.UrlWithOutSites
import com.ibrahim.dev.mercado_ibra.details.data.network.ProductDetailApi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class ProductDetailApiModule {

    @Reusable
    @Provides
    fun providerApi(@UrlWithOutSites retrofit: Retrofit): ProductDetailApi {
        return retrofit.create(ProductDetailApi::class.java)
    }
}