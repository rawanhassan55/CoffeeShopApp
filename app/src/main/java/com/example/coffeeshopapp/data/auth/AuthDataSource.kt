package com.example.coffeeshopapp.data.auth

interface AuthDataSource {

    fun getCurrentUserId(): String?

    fun isUserLoggedIn(): Boolean
}