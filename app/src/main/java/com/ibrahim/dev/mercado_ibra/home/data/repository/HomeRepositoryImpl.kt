package com.ibrahim.dev.mercado_ibra.home.data.repository

import com.ibrahim.dev.mercado_ibra.commons.network.HandlerResultHelper
import com.ibrahim.dev.mercado_ibra.commons.network.RequestStatus
import com.ibrahim.dev.mercado_ibra.home.data.contracts.HomeRepository
import com.ibrahim.dev.mercado_ibra.home.data.network.HomeApi
import com.ibrahim.dev.mercado_ibra.home.domain.models.ProductListModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val api: HomeApi
) : HomeRepository {
    override fun searchByCategories(category: String): Flow<RequestStatus<List<ProductListModel>>> {
        return flow {
            emit(RequestStatus.Loading)
            emit(HandlerResultHelper.getResult { api.getProductByCategories(category).toModel() })
        }
    }

    override fun searchByQuery(query: String): Flow<RequestStatus<List<ProductListModel>>> {
        return flow {
            emit(RequestStatus.Loading)
            emit(HandlerResultHelper.getResult { api.getProductByQuery(query).toModel() })
        }
    }
}