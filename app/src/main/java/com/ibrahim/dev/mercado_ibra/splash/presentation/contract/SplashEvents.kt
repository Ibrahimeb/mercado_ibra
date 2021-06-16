package com.ibrahim.dev.mercado_ibra.splash.presentation.contract

import com.ibrahim.dev.mercado_ibra.home.domain.models.CategoriesModel
import com.ibrahim.dev.mercado_ibra.splash.domain.models.SitesModel

sealed class SplashEvents {
    class CategoriesSuccess(val list: List<CategoriesModel>) : SplashEvents()
    class SitesSuccess(val list: List<SitesModel>) : SplashEvents()
    object ErrorCategoriesRequest : SplashEvents()
}
