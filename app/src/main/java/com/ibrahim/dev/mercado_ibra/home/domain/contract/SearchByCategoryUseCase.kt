package com.ibrahim.dev.mercado_ibra.home.domain.contract

import com.ibrahim.dev.mercado_ibra.commons.network.RequestStatus
import com.ibrahim.dev.mercado_ibra.home.domain.models.ProductListModel
import kotlinx.coroutines.flow.Flow

interface SearchByCategoryUseCase {

    fun search(category: String): Flow<RequestStatus<List<ProductListModel>>>
}