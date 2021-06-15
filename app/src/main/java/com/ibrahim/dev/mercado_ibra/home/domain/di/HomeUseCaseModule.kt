package com.ibrahim.dev.mercado_ibra.home.domain.di

import com.ibrahim.dev.mercado_ibra.home.domain.contract.SearchByCategoryUseCase
import com.ibrahim.dev.mercado_ibra.home.domain.contract.SearchByQueryUseCase
import com.ibrahim.dev.mercado_ibra.home.domain.usecase.SearchByCategoryUseCaseImpl
import com.ibrahim.dev.mercado_ibra.home.domain.usecase.SearchByQueryUseCaseImpl
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

    @Binds
    abstract fun bindsUseCaseQueryByCategory(
        useCase: SearchByQueryUseCaseImpl
    ): SearchByQueryUseCase

}