package com.example.coffeeshopapp.data.repository.firebase.auth

import com.example.coffeeshopapp.utils.result.ResultWrapper
import com.google.firebase.auth.FirebaseUser

interface FirebaseAuthRepository{

    suspend fun signUp(email: String, password: String): ResultWrapper<FirebaseUser?>

    suspend fun login(email: String, password: String): ResultWrapper<FirebaseUser?>

    suspend fun sendEmailVerification(user: FirebaseUser): ResultWrapper<Boolean>

    suspend fun resetPassword(email: String): ResultWrapper<Boolean>

    suspend fun confirmPasswordReset(code: String, newPassword: String): ResultWrapper<Boolean>

    fun getCurrentUser(): FirebaseUser?

    fun logout()
}
