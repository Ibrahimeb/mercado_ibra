package com.ibrahim.dev.mercado_ibra.splash.domain.di

import com.ibrahim.dev.mercado_ibra.splash.domain.contract.SplashCategoriesUseCase
import com.ibrahim.dev.mercado_ibra.splash.domain.usecase.SplashCategoriesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class SplashUseCaseModule {
    @Binds
    abstract fun bindsUseCaseModule(categoriesUseCase: SplashCategoriesUseCaseImpl): SplashCategoriesUseCase
}