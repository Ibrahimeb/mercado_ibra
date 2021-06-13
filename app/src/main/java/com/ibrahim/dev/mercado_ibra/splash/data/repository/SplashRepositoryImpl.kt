package com.ibrahim.dev.mercado_ibra.splash.data.repository

import com.ibrahim.dev.mercado_ibra.splash.data.contract.SplashRepository
import com.ibrahim.dev.mercado_ibra.splash.data.network.SplashApi
import com.ibrahim.dev.mercado_ibra.splash.domain.models.CategoryModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SplashRepositoryImpl @Inject constructor(
    private val api: SplashApi
) : SplashRepository {
    override suspend fun getCategories(): Flow<List<CategoryModel>> {
        return flow {
           emit(api.getCategories().map { it.toModel() })
        }
    }
}