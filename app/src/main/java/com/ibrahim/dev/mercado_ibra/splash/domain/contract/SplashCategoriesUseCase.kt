package com.ibrahim.dev.mercado_ibra.splash.domain.contract

import com.ibrahim.dev.mercado_ibra.commons.network.RequestStatus
import com.ibrahim.dev.mercado_ibra.splash.domain.models.CategoriesModel
import kotlinx.coroutines.flow.Flow

interface SplashCategoriesUseCase {

   suspend fun getCategories(): Flow<RequestStatus<List<CategoriesModel>>>
}