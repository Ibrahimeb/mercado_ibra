package com.ibrahim.dev.mercado_ibra.categories.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.ibrahim.dev.mercado_ibra.commons.adapter.ViewTypeVh
import com.ibrahim.dev.mercado_ibra.splash.domain.models.CategoriesModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor() : ViewModel() {

    fun mapListAdapterList(listCategories: List<CategoriesModel>): MutableList<ViewTypeVh> {
        return listCategories.map { ViewTypeVh.ProductCategories(it) }.toMutableList()
    }
}