package com.ibrahim.dev.mercado_ibra.commons.adapter

import com.ibrahim.dev.mercado_ibra.splash.domain.models.CategoriesModel

sealed class ViewTypeVh {
    class ProductCategories(val categoriesModel: CategoriesModel): ViewTypeVh()
}
