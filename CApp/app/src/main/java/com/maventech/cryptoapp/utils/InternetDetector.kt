package com.matecho.wms.utils

import android.content.Context
import android.net.ConnectivityManager


class InternetDetector(private var context: Context) {
    var cntext:Context
    init {
        cntext=context
    }

    companion object {

        public fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
            val activeNetworkInfo = connectivityManager!!.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }
    }
}