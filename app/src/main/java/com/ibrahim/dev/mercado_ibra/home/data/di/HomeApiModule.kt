package com.ibrahim.dev.mercado_ibra.home.data.di

import com.ibrahim.dev.mercado_ibra.home.data.network.HomeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class HomeApiModule {

    @Provides
    fun providerApiHome(retrofit: Retrofit): HomeApi {
        return retrofit.create(HomeApi::class.java)
    }
}