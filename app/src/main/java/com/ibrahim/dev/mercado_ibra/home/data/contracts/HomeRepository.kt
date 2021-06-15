package com.ibrahim.dev.mercado_ibra.home.data.contracts

import com.ibrahim.dev.mercado_ibra.commons.network.RequestStatus
import com.ibrahim.dev.mercado_ibra.home.domain.models.ProductListModel
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    fun searchByCategories(category: String): Flow<RequestStatus<List<ProductListModel>>>

    fun searchByQuery(query: String): Flow<RequestStatus<List<ProductListModel>>>
}