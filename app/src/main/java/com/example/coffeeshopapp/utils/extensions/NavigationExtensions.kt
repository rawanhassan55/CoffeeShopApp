package com.example.coffeeshopapp.utils.extensions

import android.os.Bundle
import android.util.Log
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

fun Fragment.navigateSafely(
    @IdRes action: Int,
    args: Bundle? = null
) {
    val navController = findNavController()

    try {
        if (navController.currentDestination?.getAction(action) != null) {
            navController.navigate(action, args)
        }
    } catch (e: Exception) {
        Log.e("NAV_ERROR", e.message ?: "Navigation failed")
    }
}

fun Fragment.goBack(){
    findNavController().popBackStack()
}