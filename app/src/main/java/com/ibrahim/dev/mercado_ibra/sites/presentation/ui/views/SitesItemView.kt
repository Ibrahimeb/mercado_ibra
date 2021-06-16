package com.ibrahim.dev.mercado_ibra.sites.presentation.ui.views

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ibrahim.dev.mercado_ibra.commons.adapter.EventsAdapter
import com.ibrahim.dev.mercado_ibra.commons.adapter.ItemViewHolder
import com.ibrahim.dev.mercado_ibra.commons.adapter.ViewTypeVh
import com.ibrahim.dev.mercado_ibra.databinding.ItemSitesBinding

class SitesItemView(private val viewBinding: ItemSitesBinding) :
    ItemViewHolder(viewBinding.root) {

    companion object {
        fun from(viewGroup: ViewGroup): SitesItemView {
            val binding = ItemSitesBinding
                .inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
            return SitesItemView(binding)
        }
    }


    override fun render(action: (EventsAdapter) -> Unit, dataItem: ViewTypeVh) {
        val data = (dataItem as ViewTypeVh.Sites).item
        viewBinding.apply {
            textView.text = data.name
            root.setOnClickListener {
                action.invoke(EventsAdapter.SelectedItem(adapterPosition, dataItem))
            }
        }
    }
}