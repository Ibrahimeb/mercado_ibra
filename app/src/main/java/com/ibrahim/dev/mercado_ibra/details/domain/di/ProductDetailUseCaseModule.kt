package com.ibrahim.dev.mercado_ibra.details.domain.di

import com.ibrahim.dev.mercado_ibra.details.domain.contract.ProductDetailUseCase
import com.ibrahim.dev.mercado_ibra.details.domain.useCase.ProductDetailUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ProductDetailUseCaseModule {
    @Binds
    abstract fun bindsUseCase(UseCase: ProductDetailUseCaseImpl): ProductDetailUseCase
}