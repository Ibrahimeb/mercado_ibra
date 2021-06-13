package com.ibrahim.dev.mercado_ibra.splash.data.contract

import com.ibrahim.dev.mercado_ibra.splash.domain.models.CategoryModel
import kotlinx.coroutines.flow.Flow

interface SplashRepository {

  suspend  fun getCategories(): Flow<List<CategoryModel>>
}