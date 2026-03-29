package com.example.coffeeshopapp.viewmodel.auth

import com.google.firebase.auth.FirebaseUser

data class AuthUiState(
    val isLoading: Boolean = false,
    val user: FirebaseUser? = null,
    val isEmailVerified: Boolean = false
)
