package com.ibrahim.dev.mercado_ibra.home.presentation.viewmodels

import com.ibrahim.dev.mercado_ibra.commons.network.RequestStatus
import com.ibrahim.dev.mercado_ibra.home.domain.models.CategoriesModel
import com.ibrahim.dev.mercado_ibra.home.domain.models.ProductListModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object FakeModels {
    const val CODE_ERROR = 404
    const val MSG_ERROR = "not found"

    fun mockResponseCategoriesUseCase(): Flow<RequestStatus<List<CategoriesModel>>> {
        val model = CategoriesModel(
            "MCO12345",
            "productos y servicios",
            false
        )
        val list = listOf(model, model, model, model)
        return flow {
            emit(RequestStatus.Success(list))
        }
    }

    fun mockResponseCategoriesUseCaseError(): Flow<RequestStatus<List<CategoriesModel>>> {
        return flow {
            emit(RequestStatus.Error(CODE_ERROR, MSG_ERROR))
        }
    }

    fun mockResponseSearchProduct(): Flow<RequestStatus<List<ProductListModel>>> {
        val model = ProductListModel(
            id = "1234567",
            title = "product",
            urlImage = "image",
            price = 3000.34,
            currency = "COP",
            availableQuantity = 4,
            sellerQuantity = 6,
            city = "bogota",
            hasMercadoPago = true,
            hasFreeShipping = true

        )
        val list = listOf(model, model, model, model)

        return flow {
            emit(RequestStatus.Success(list))
        }
    }

    fun mockResponseSearchProductEmptyResult(): Flow<RequestStatus<List<ProductListModel>>> {
        val list = emptyList<ProductListModel>()
        return flow {
            emit(RequestStatus.Success(list))
        }
    }

    fun mockResponseSearchProductFailRequest(): Flow<RequestStatus<List<ProductListModel>>> {
        return flow {
            emit(RequestStatus.Error(CODE_ERROR, MSG_ERROR))
        }
    }


}