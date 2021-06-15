package com.ibrahim.dev.mercado_ibra.home.data.di

import com.ibrahim.dev.mercado_ibra.home.data.contracts.HomeRepository
import com.ibrahim.dev.mercado_ibra.home.data.repository.HomeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class HomeRepositoryModule {

    @Binds
    abstract fun binsHomeRepository(repository: HomeRepositoryImpl): HomeRepository
}