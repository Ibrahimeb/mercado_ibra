package com.ibrahim.dev.mercado_ibra.commons.adapter

import com.ibrahim.dev.mercado_ibra.home.domain.models.CategoriesModel
import com.ibrahim.dev.mercado_ibra.home.domain.models.ProductListModel
import com.ibrahim.dev.mercado_ibra.splash.domain.models.SitesModel

sealed class ViewTypeVh {
    class Sites(val item: SitesModel) : ViewTypeVh()
    class ProductCategories(val item: CategoriesModel) : ViewTypeVh()
    class ProductListBySearch(val item: ProductListModel) : ViewTypeVh()
}
