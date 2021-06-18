package com.ibrahim.dev.mercado_ibra.home.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoriesModel(
    val code: String,
    val name: String,
    val isSelected: Boolean = false
) : Parcelable
