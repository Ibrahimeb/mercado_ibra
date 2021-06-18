package com.ibrahim.dev.mercado_ibra.details.domain.useCase

import com.ibrahim.dev.mercado_ibra.commons.network.RequestStatus
import com.ibrahim.dev.mercado_ibra.details.data.contracts.ProductDetailsRepository
import com.ibrahim.dev.mercado_ibra.details.domain.contract.ProductDetailUseCase
import com.ibrahim.dev.mercado_ibra.details.domain.models.ProductDetailsModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductDetailUseCaseImpl @Inject constructor(
    private val repository: ProductDetailsRepository
) : ProductDetailUseCase {

    override fun getProductDetail(productId: String): Flow<RequestStatus<ProductDetailsModel>> {
        return repository.getItemsById(productId)
    }
}