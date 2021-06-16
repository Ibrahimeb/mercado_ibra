package com.ibrahim.dev.mercado_ibra.commons.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.view.View
import android.widget.Toast

fun Context.hasNetwork(): Boolean {
    val connectivityManager: ConnectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
    return activeNetwork != null && activeNetwork.isConnected
}

fun String?.orAlternative(alternative: String): String {
    return this ?: alternative
}

fun Int?.orZero(): Int {
    return this ?: 0
}

fun Boolean?.orFalse(): Boolean {
    return this ?: false
}

fun View.showOrHide(isVisibility: Boolean) {
    if (isVisibility) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}

fun Context.makeToast(msg:String){
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
