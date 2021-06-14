package com.ibrahim.dev.mercado_ibra.splash.presentation.contract

import com.ibrahim.dev.mercado_ibra.splash.domain.models.CategoriesModel

sealed class SplashEvents {
    class CategoriesSuccess(val list: List<CategoriesModel>) : SplashEvents()
    object ErrorCategoriesRequest : SplashEvents()
}
