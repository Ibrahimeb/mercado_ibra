package com.ibrahim.dev.mercado_ibra.commons.adapter

import com.ibrahim.dev.mercado_ibra.home.domain.models.ProductListModel
import com.ibrahim.dev.mercado_ibra.splash.domain.models.CategoriesModel

sealed class ViewTypeVh {
    class ProductCategories(val item: CategoriesModel) : ViewTypeVh()
    class ProductListBySearch(val item: ProductListModel) : ViewTypeVh()
}
