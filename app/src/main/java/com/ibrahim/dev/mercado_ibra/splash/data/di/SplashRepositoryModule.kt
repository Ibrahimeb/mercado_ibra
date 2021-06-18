package com.ibrahim.dev.mercado_ibra.splash.data.di

import com.ibrahim.dev.mercado_ibra.splash.data.contract.SplashRepository
import com.ibrahim.dev.mercado_ibra.splash.data.repository.SplashRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class SplashRepositoryModule {
    @Binds
    abstract fun bindsRepository(repository: SplashRepositoryImpl): SplashRepository
}