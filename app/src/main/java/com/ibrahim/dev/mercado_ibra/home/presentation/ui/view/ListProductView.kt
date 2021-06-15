package com.ibrahim.dev.mercado_ibra.home.presentation.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.ibrahim.dev.mercado_ibra.R
import com.ibrahim.dev.mercado_ibra.commons.adapter.EventsAdapter
import com.ibrahim.dev.mercado_ibra.commons.adapter.ItemViewHolder
import com.ibrahim.dev.mercado_ibra.commons.adapter.ViewTypeVh
import com.ibrahim.dev.mercado_ibra.commons.utils.showOrHide
import com.ibrahim.dev.mercado_ibra.databinding.ItemProductListBinding

class ListProductView(private val viewBinding: ItemProductListBinding) :
    ItemViewHolder(viewBinding.root) {
    companion object {
        fun from(viewGroup: ViewGroup): ListProductView {
            val binding = ItemProductListBinding
                .inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
            return ListProductView(binding)
        }
    }

    override fun render(action: (EventsAdapter) -> Unit, dataItem: ViewTypeVh) {
        val data = (dataItem as ViewTypeVh.ProductListBySearch).item
        viewBinding.apply {
            textViewTitle.text = data.title
            textViewCity.text = data.city
            textViewPrice.text = "$ ${data.price.toString()}"
            textViewFreeShippingLabel.showOrHide(data.hasFreeShipping)

            Glide.with(viewBinding.root.context)
                .load(data.urlImage)
                .placeholder(R.drawable.placeholder)
                .centerCrop()
                .into(imageViewProductImg)

            root.setOnClickListener {
                action.invoke(EventsAdapter.SelectedItem(adapterPosition, dataItem = dataItem))
            }
        }
    }
}