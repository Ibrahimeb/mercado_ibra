package com.ibrahim.dev.mercado_ibra.details.data.repository

import com.ibrahim.dev.mercado_ibra.commons.network.HandlerResultHelper
import com.ibrahim.dev.mercado_ibra.commons.network.RequestStatus
import com.ibrahim.dev.mercado_ibra.details.data.contracts.ProductDetailsRepository
import com.ibrahim.dev.mercado_ibra.details.data.network.ProductDetailApi
import com.ibrahim.dev.mercado_ibra.details.domain.models.ProductDetailsModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductDetailRepositoryImpl @Inject constructor(
    private val api: ProductDetailApi
) : ProductDetailsRepository {
    override fun getItemsById(productId: String): Flow<RequestStatus<ProductDetailsModel>> {
        return flow {
            emit(RequestStatus.Loading)
            emit(HandlerResultHelper.getResult { (api.getItem(productId)[0].toModel()) })
        }
    }
}