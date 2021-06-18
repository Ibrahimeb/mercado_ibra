package com.ibrahim.dev.mercado_ibra.commons.network

sealed class RequestStatus<out T> {
    data class Success<out T>(val value: T) : RequestStatus<T>()
    data class Error(val code: Int? = null, val msg: String) : RequestStatus<Nothing>()
    object Loading : RequestStatus<Nothing>()
}
