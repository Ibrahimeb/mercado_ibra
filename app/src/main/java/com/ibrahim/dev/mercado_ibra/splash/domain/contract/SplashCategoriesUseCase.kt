package com.ibrahim.dev.mercado_ibra.splash.domain.contract

import com.ibrahim.dev.mercado_ibra.splash.domain.models.CategoryModel
import kotlinx.coroutines.flow.Flow

interface SplashCategoriesUseCase {

   suspend fun getCategories(): Flow<List<CategoryModel>>
}