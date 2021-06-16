package com.ibrahim.dev.mercado_ibra.home.presentation.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ibrahim.dev.mercado_ibra.R
import com.ibrahim.dev.mercado_ibra.commons.adapter.EventsAdapter
import com.ibrahim.dev.mercado_ibra.commons.adapter.ItemViewHolder
import com.ibrahim.dev.mercado_ibra.commons.adapter.ViewTypeVh
import com.ibrahim.dev.mercado_ibra.databinding.ItemCategoryBinding

class CategoryView(private val viewBinding: ItemCategoryBinding) :
    ItemViewHolder(viewBinding.root) {
    companion object {
        fun from(viewGroup: ViewGroup): CategoryView {
            val binding = ItemCategoryBinding
                .inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
            return CategoryView(binding)
        }
    }

    override fun render(action: (EventsAdapter) -> Unit, dataItem: ViewTypeVh) {
        val data = (dataItem as ViewTypeVh.ProductCategories).item
        viewBinding.apply {
            textViewTitle.text = data.name
            if (data.isSelected) {
                textViewTitle.setTextColor(viewBinding.root.context.getColor(R.color.white))
                cardViewContainer.setCardBackgroundColor(viewBinding.root.context.getColor(R.color.black))
            }
        }

    }
}