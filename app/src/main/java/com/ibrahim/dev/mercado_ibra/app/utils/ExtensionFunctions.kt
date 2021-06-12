package com.ibrahim.dev.mercado_ibra.app.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

fun Context.hasNetwork(): Boolean {
    val connectivityManager: ConnectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
    return activeNetwork != null && activeNetwork.isConnected
}
