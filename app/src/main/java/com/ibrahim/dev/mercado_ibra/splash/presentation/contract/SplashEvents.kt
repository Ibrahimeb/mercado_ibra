package com.ibrahim.dev.mercado_ibra.splash.presentation.contract

import com.ibrahim.dev.mercado_ibra.splash.domain.models.CategoryModel

sealed class SplashEvents {
    class CategoriesSuccess(val list: List<CategoryModel>) : SplashEvents()
    object ErrorCategoriesRequest : SplashEvents()
}
