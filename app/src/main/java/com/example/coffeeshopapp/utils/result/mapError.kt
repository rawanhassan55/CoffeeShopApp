package com.example.coffeeshopapp.utils.result

import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.*
import com.google.firebase.firestore.FirebaseFirestoreException



fun mapError(e: Exception): String {
    return when (e) {
        // Firebase Auth Errors
        is FirebaseAuthWeakPasswordException ->
            e.reason ?: "Password is too weak. It must be at least 6 characters."

        is FirebaseAuthInvalidCredentialsException ->
            "Invalid email or password."

        is FirebaseAuthInvalidUserException ->
            "This account does not exist or has been disabled."

        is FirebaseAuthUserCollisionException ->
            "This email address is already registered."

        is FirebaseAuthRecentLoginRequiredException ->
            "Please log in again to perform this action."

        is FirebaseAuthActionCodeException ->
            "This link is no longer valid. Please request a new one."

        is FirebaseAuthException ->
            "Authentication failed. Please try again."

        // Firebase Fire store Errors
        is FirebaseFirestoreException -> {
            when (e.code) {
                FirebaseFirestoreException.Code.PERMISSION_DENIED ->
                    "You don't have permission to perform this action."
                FirebaseFirestoreException.Code.UNAVAILABLE ->
                    "data service is currently unavailable. Try again later."
                FirebaseFirestoreException.Code.ABORTED ->
                    "Operation was aborted. Please try again."
                FirebaseFirestoreException.Code.CANCELLED ->
                    "Operation was cancelled. Please try again."
                FirebaseFirestoreException.Code.UNKNOWN ->
                    "Unknown data error occurred."
                FirebaseFirestoreException.Code.NOT_FOUND -> "Product not found."
                else -> "data error: ${e.message}"
            }
        }

        // Network issues
        is FirebaseNetworkException ->
            "No internet connection. Please check your network."

        // Unknown errors
        else ->
            "Something went wrong. Please try again."
    }
}
