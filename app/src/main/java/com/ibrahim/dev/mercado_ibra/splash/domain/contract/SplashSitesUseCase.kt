package com.ibrahim.dev.mercado_ibra.splash.domain.contract

import com.ibrahim.dev.mercado_ibra.commons.network.RequestStatus
import com.ibrahim.dev.mercado_ibra.home.domain.models.CategoriesModel
import com.ibrahim.dev.mercado_ibra.splash.domain.models.SitesModel
import kotlinx.coroutines.flow.Flow

interface SplashSitesUseCase {

   suspend fun getSites(): Flow<RequestStatus<List<SitesModel>>>
}