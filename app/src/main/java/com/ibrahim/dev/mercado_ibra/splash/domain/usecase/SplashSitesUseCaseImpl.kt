package com.ibrahim.dev.mercado_ibra.splash.domain.usecase

import com.ibrahim.dev.mercado_ibra.commons.network.RequestStatus
import com.ibrahim.dev.mercado_ibra.splash.data.contract.SplashRepository
import com.ibrahim.dev.mercado_ibra.splash.domain.contract.SplashSitesUseCase
import com.ibrahim.dev.mercado_ibra.home.domain.models.CategoriesModel
import com.ibrahim.dev.mercado_ibra.splash.domain.models.SitesModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SplashSitesUseCaseImpl @Inject constructor(
    private val repository: SplashRepository
) : SplashSitesUseCase {

    override suspend fun getSites(): Flow<RequestStatus<List<SitesModel>>> {
        return repository.getSites()
    }
}