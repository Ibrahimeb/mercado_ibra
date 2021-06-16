package com.ibrahim.dev.mercado_ibra.sites.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.ibrahim.dev.mercado_ibra.commons.adapter.ViewTypeVh
import com.ibrahim.dev.mercado_ibra.splash.domain.models.SitesModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SitesViewModel @Inject constructor() : ViewModel() {

    fun mapListAdapterList(listCategories: List<SitesModel>): MutableList<ViewTypeVh> {
        return listCategories.map { ViewTypeVh.Sites(it) }.toMutableList()
    }
}