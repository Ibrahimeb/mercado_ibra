package com.ibrahim.dev.mercado_ibra.details.presentation.contract

import com.ibrahim.dev.mercado_ibra.details.domain.models.ProductDetailsModel

sealed class DetailsEvent {
    class Loading(val isLoading: Boolean) : DetailsEvent()
    class ErrorRequest(val msg: String) : DetailsEvent()
    class SuccessRequest(val product: ProductDetailsModel) : DetailsEvent()
}
