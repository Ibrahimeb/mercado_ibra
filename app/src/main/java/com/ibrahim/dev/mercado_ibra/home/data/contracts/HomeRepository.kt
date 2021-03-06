package com.ibrahim.dev.mercado_ibra.home.data.contracts

import com.ibrahim.dev.mercado_ibra.commons.network.RequestStatus
import com.ibrahim.dev.mercado_ibra.home.domain.models.ProductListModel
import com.ibrahim.dev.mercado_ibra.home.domain.models.CategoriesModel
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    fun getCategories(sitesCode: String): Flow<RequestStatus<List<CategoriesModel>>>

    fun searchByCategories(category: String, sitesCode: String): Flow<RequestStatus<List<ProductListModel>>>

    fun searchByQuery(query: String, sitesCode: String): Flow<RequestStatus<List<ProductListModel>>>
}