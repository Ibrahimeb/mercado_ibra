package com.ibrahim.dev.mercado_ibra.splash.domain.usecase

import com.ibrahim.dev.mercado_ibra.splash.data.contract.SplashRepository
import com.ibrahim.dev.mercado_ibra.splash.domain.contract.SplashCategoriesUseCase
import com.ibrahim.dev.mercado_ibra.splash.domain.models.CategoryModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SplashCategoriesUseCaseImpl @Inject constructor(
    private val repository: SplashRepository
) : SplashCategoriesUseCase {

    override suspend fun getCategories(): Flow<List<CategoryModel>> {
        return repository.getCategories()
    }
}