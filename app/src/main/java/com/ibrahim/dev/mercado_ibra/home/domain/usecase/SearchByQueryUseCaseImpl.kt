package com.ibrahim.dev.mercado_ibra.home.domain.usecase

import com.ibrahim.dev.mercado_ibra.commons.network.RequestStatus
import com.ibrahim.dev.mercado_ibra.home.data.contracts.HomeRepository
import com.ibrahim.dev.mercado_ibra.home.domain.contract.SearchByQueryUseCase
import com.ibrahim.dev.mercado_ibra.home.domain.models.ProductListModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchByQueryUseCaseImpl @Inject constructor(
    private val repository: HomeRepository
) : SearchByQueryUseCase {

    override fun search(category: String): Flow<RequestStatus<List<ProductListModel>>> {
        return repository.searchByQuery(category)
    }
}