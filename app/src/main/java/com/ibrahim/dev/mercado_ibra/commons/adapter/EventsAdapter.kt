package com.ibrahim.dev.mercado_ibra.commons.adapter

sealed class EventsAdapter {
    class SelectedItem(val pos: Int, val dataItem: ViewTypeVh) : EventsAdapter()
}
