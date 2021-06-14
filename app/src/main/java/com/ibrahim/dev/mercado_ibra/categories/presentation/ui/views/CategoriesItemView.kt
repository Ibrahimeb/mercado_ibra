package com.ibrahim.dev.mercado_ibra.categories.presentation.ui.views

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ibrahim.dev.mercado_ibra.commons.adapter.EventsAdapter
import com.ibrahim.dev.mercado_ibra.commons.adapter.ItemViewHolder
import com.ibrahim.dev.mercado_ibra.commons.adapter.ViewTypeVh
import com.ibrahim.dev.mercado_ibra.databinding.ItemCategoriesBinding

class CategoriesItemView(private val viewBinding: ItemCategoriesBinding) :
    ItemViewHolder(viewBinding.root) {

    companion object {
        fun from(viewGroup: ViewGroup): CategoriesItemView {
            val binding = ItemCategoriesBinding
                .inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
            return CategoriesItemView(binding)
        }
    }


    override fun render(action: (EventsAdapter) -> Unit, dataItem: ViewTypeVh) {
        val data = (dataItem as ViewTypeVh.ProductCategories).categoriesModel
        viewBinding.apply {
            textView.text = data.name
            root.setOnClickListener {
                action.invoke(EventsAdapter.SelectedItem(adapterPosition, dataItem))
            }
        }
    }
}