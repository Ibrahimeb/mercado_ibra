package com.ibrahim.dev.mercado_ibra.home.domain.usecase

import com.ibrahim.dev.mercado_ibra.commons.network.RequestStatus
import com.ibrahim.dev.mercado_ibra.home.data.contracts.HomeRepository
import com.ibrahim.dev.mercado_ibra.home.domain.contract.CategoriesUseCase
import com.ibrahim.dev.mercado_ibra.home.domain.models.CategoriesModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoriesUseCaseImpl @Inject constructor(
    private val repository: HomeRepository
) : CategoriesUseCase {

    override suspend fun getCategories(sitesId: String): Flow<RequestStatus<List<CategoriesModel>>> {
        return repository.getCategories(sitesId)
    }
}