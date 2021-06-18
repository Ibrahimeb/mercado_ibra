package com.ibrahim.dev.mercado_ibra.commons.adapter

import androidx.recyclerview.widget.DiffUtil

class AdapterDiff : DiffUtil.ItemCallback<ViewTypeVh>() {
    override fun areItemsTheSame(oldItem: ViewTypeVh, newItem: ViewTypeVh): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ViewTypeVh, newItem: ViewTypeVh): Boolean {
        return oldItem == newItem
    }
}