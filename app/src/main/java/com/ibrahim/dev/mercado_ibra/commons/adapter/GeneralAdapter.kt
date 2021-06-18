package com.ibrahim.dev.mercado_ibra.commons.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.ibrahim.dev.mercado_ibra.home.presentation.ui.view.CategoryView
import com.ibrahim.dev.mercado_ibra.home.presentation.ui.view.ListProductView
import com.ibrahim.dev.mercado_ibra.sites.presentation.ui.views.SitesItemView

class GeneralAdapter(private val callback: (EventsAdapter) -> Unit) :
    ListAdapter<ViewTypeVh, ItemViewHolder>(AdapterDiff()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return when (viewType) {
            SITES_VIEW -> SitesItemView.from(parent)
            CATEGORIES_VIEW -> CategoryView.from(parent)
            PRODUCT_LIST_VIEW -> ListProductView.from(parent)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.render(callback, getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is ViewTypeVh.ProductCategories -> CATEGORIES_VIEW
            is ViewTypeVh.ProductListBySearch -> PRODUCT_LIST_VIEW
            is ViewTypeVh.Sites -> SITES_VIEW
            else -> NO_VIEW_FOUND
        }
    }

    companion object {
        const val CATEGORIES_VIEW = 1
        const val PRODUCT_LIST_VIEW = 2
        const val SITES_VIEW = 3
        const val NO_VIEW_FOUND = -1
    }
}