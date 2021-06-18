package com.ibrahim.dev.mercado_ibra.details.data.contracts

import com.ibrahim.dev.mercado_ibra.commons.network.RequestStatus
import com.ibrahim.dev.mercado_ibra.details.domain.models.ProductDetailsModel
import kotlinx.coroutines.flow.Flow

interface ProductDetailsRepository {

    fun getItemsById(productId: String): Flow<RequestStatus<ProductDetailsModel>>
}


