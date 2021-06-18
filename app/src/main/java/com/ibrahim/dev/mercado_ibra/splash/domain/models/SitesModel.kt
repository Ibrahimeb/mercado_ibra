package com.ibrahim.dev.mercado_ibra.splash.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SitesModel(
    val default_currency_id: String,
    val id: String,
    val name: String
) : Parcelable