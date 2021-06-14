package com.ibrahim.dev.mercado_ibra.splash.data.repository

import com.ibrahim.dev.mercado_ibra.commons.network.HandlerResultHelper
import com.ibrahim.dev.mercado_ibra.commons.network.RequestStatus
import com.ibrahim.dev.mercado_ibra.splash.data.contract.SplashRepository
import com.ibrahim.dev.mercado_ibra.splash.data.network.SplashApi
import com.ibrahim.dev.mercado_ibra.splash.domain.models.CategoriesModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SplashRepositoryImpl @Inject constructor(
    private val api: SplashApi
) : SplashRepository {
    override suspend fun getCategories(): Flow<RequestStatus<List<CategoriesModel>>> {
        return flow {
            emit(RequestStatus.Loading)
            emit(HandlerResultHelper.getResult { api.getCategories().map { it.toModel() } })
        }
    }
}