package com.ibrahim.dev.mercado_ibra.app.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ibrahim.dev.mercado_ibra.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun providerBaseUrl(@ApplicationContext context: Context) = context.getString(R.string.base_url)

    @Provides
    fun providerGson(): Gson = GsonBuilder().create()

    @Provides
    fun providerRetrofit(okHttpClient: OkHttpClient, gson: Gson, url: String): Retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()
}