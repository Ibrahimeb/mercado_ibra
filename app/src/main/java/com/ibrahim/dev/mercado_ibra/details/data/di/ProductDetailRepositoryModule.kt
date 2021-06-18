package com.ibrahim.dev.mercado_ibra.details.data.di

import com.ibrahim.dev.mercado_ibra.details.data.contracts.ProductDetailsRepository
import com.ibrahim.dev.mercado_ibra.details.data.repository.ProductDetailRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ProductDetailRepositoryModule {
    @Binds
    abstract fun bindsRepository(repository: ProductDetailRepositoryImpl): ProductDetailsRepository
}