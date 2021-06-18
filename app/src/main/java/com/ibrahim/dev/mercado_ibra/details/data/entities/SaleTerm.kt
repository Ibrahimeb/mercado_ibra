package com.ibrahim.dev.mercado_ibra.details.data.entities


import com.google.gson.annotations.SerializedName

data class SaleTerm(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("value_id")
    val valueId: String?,
    @SerializedName("value_name")
    val valueName: String?,
    @SerializedName("value_struct")
    val valueStruct: Any?,
    @SerializedName("values")
    val values: List<ValueX>?
)