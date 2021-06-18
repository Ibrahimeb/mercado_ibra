package com.ibrahim.dev.mercado_ibra.details.presentation.viewmodels

import com.ibrahim.dev.mercado_ibra.commons.network.RequestStatus
import com.ibrahim.dev.mercado_ibra.details.domain.models.ProductDetailsModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object FakeModules {
    const val CODE_ERROR = 404
    const val MSG_ERROR = "not found"

    fun getProductDetailModel(hasError: Boolean): Flow<RequestStatus<ProductDetailsModel>> {
        val model = ProductDetailsModel(
            true,
            5,
            "bogota",
            "cop",
            "",
            8,
            45.0,
            3,
            "",
            "",
            "",
            freeShipping = true
        )
        return flow {
            emit(
                if (hasError) RequestStatus.Error(
                    CODE_ERROR,
                    MSG_ERROR
                ) else RequestStatus.Success(model)
            )
        }
    }


}