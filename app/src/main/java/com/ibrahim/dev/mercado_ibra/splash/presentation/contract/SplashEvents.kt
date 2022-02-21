package com.ibrahim.dev.mercado_ibra.splash.presentation.contract

import com.ibrahim.dev.mercado_ibra.splash.domain.models.SitesModel

sealed class SplashEvents {
    class SitesSuccess(val list: List<SitesModel>) : SplashEvents()
    object ErrorSitesRequest : SplashEvents()
}
