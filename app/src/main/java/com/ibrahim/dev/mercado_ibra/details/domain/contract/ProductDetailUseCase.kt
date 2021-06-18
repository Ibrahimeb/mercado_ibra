package com.ibrahim.dev.mercado_ibra.details.domain.contract

import com.ibrahim.dev.mercado_ibra.commons.network.RequestStatus
import com.ibrahim.dev.mercado_ibra.details.domain.models.ProductDetailsModel
import kotlinx.coroutines.flow.Flow

interface ProductDetailUseCase {

    fun getProductDetail(productId: String): Flow<RequestStatus<ProductDetailsModel>>
}