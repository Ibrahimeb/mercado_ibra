package com.ibrahim.dev.mercado_ibra.home.domain.usecase

import com.ibrahim.dev.mercado_ibra.commons.network.RequestStatus
import com.ibrahim.dev.mercado_ibra.home.data.contracts.HomeRepository
import com.ibrahim.dev.mercado_ibra.home.domain.contract.SearchByCategoryUseCase
import com.ibrahim.dev.mercado_ibra.home.domain.models.ProductListModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchByCategoryUseCaseImpl @Inject constructor(
    private val repository: HomeRepository
) : SearchByCategoryUseCase {

    override fun search(
        category: String,
        sitesCode: String
    ): Flow<RequestStatus<List<ProductListModel>>> {
        return repository.searchByCategories(category,sitesCode)
    }
}