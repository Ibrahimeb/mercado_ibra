package com.ibrahim.dev.mercado_ibra.splash.data.contract

import com.ibrahim.dev.mercado_ibra.commons.network.RequestStatus
import com.ibrahim.dev.mercado_ibra.splash.domain.models.CategoriesModel
import kotlinx.coroutines.flow.Flow

interface SplashRepository {

  fun getCategories(): Flow<RequestStatus<List<CategoriesModel>>>
}