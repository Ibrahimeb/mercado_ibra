package com.ibrahim.dev.mercado_ibra.splash.domain.usecase

import com.ibrahim.dev.mercado_ibra.commons.network.RequestStatus
import com.ibrahim.dev.mercado_ibra.splash.data.contract.SplashRepository
import com.ibrahim.dev.mercado_ibra.splash.domain.contract.SplashCategoriesUseCase
import com.ibrahim.dev.mercado_ibra.splash.domain.models.CategoriesModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SplashCategoriesUseCaseImpl @Inject constructor(
    private val repository: SplashRepository
) : SplashCategoriesUseCase {

    override suspend fun getCategories(): Flow<RequestStatus<List<CategoriesModel>>> {
        return repository.getCategories()
    }
}