package com.ibrahim.dev.mercado_ibra.home.domain.di

import com.ibrahim.dev.mercado_ibra.home.domain.contract.SearchByCategoryUseCase
import com.ibrahim.dev.mercado_ibra.home.domain.usecase.SearchByCategoryUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class HomeUseCaseModule {
    @Binds
    abstract fun bindsUseCaseSearchByCategory(
        useCase: SearchByCategoryUseCaseImpl
    ): SearchByCategoryUseCase
}