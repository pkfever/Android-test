package com.android.test.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.android.test.R

/**
 * Helper to hide the keyboard
 *
 * @param act
 */
fun hideKeyboard(act: Activity?) {
    if (act != null && act.currentFocus != null) {
        val inputMethodManager =
            act.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(act.currentFocus!!.windowToken, 0)
    }
}

fun Context.isNetworkAvailable(): Boolean {
    var isConnected = false
    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val capabilities =
        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
    if (capabilities != null) {
        when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                isConnected = true
            }
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                isConnected = true
            }
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                isConnected = true
            }
        }
    }
    Toast.makeText(applicationContext, R.string.no_internet_connection, Toast.LENGTH_SHORT).show()
    return isConnected
}
