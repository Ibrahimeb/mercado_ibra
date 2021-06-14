package com.ibrahim.dev.mercado_ibra.commons.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class ItemViewHolder(view: View) :
    RecyclerView.ViewHolder(view) {

    abstract fun render(action: (EventsAdapter) -> Unit, dataItem: ViewTypeVh)
}