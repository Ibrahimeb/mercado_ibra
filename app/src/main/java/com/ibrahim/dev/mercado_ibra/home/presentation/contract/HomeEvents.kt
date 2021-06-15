package com.ibrahim.dev.mercado_ibra.home.presentation.contract

import com.ibrahim.dev.mercado_ibra.commons.adapter.ViewTypeVh

sealed class HomeEvents {
    class Loading(val isLoading: Boolean) : HomeEvents()
    class ErrorRequest(val msg: String) : HomeEvents()
    class SuccessRequest(val listItem: List<ViewTypeVh>) : HomeEvents()
}