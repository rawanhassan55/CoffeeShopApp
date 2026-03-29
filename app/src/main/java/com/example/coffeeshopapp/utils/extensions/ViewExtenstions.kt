package com.example.coffeeshopapp.utils.extensions

import android.view.View
import com.google.android.material.snackbar.Snackbar
import android.content.Context
import android.view.inputmethod.InputMethodManager



fun View.showSnack(message: String, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, message, duration).show()
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

