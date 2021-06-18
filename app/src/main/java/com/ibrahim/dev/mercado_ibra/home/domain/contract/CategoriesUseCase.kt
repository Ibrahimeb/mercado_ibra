package com.ibrahim.dev.mercado_ibra.home.domain.contract

import com.ibrahim.dev.mercado_ibra.commons.network.RequestStatus
import com.ibrahim.dev.mercado_ibra.home.domain.models.CategoriesModel
import kotlinx.coroutines.flow.Flow

interface CategoriesUseCase {

    suspend fun getCategories(sitesId: String): Flow<RequestStatus<List<CategoriesModel>>>
}