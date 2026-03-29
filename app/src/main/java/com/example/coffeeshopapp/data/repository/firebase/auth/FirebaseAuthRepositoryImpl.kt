package com.example.coffeeshopapp.data.repository.firebase.auth

import com.example.coffeeshopapp.utils.result.ResultWrapper
import com.example.coffeeshopapp.utils.result.safeCall
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
) : FirebaseAuthRepository
{
    override suspend fun signUp(email: String, password: String): ResultWrapper<FirebaseUser?> =
        safeCall {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            result.user
        }

    override suspend fun login(email: String, password: String): ResultWrapper<FirebaseUser?> =
        safeCall {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            result.user
        }

    override suspend fun sendEmailVerification(user: FirebaseUser): ResultWrapper<Boolean> =
        safeCall {
            user.sendEmailVerification().await()
            true
        }

    override suspend fun resetPassword(email: String): ResultWrapper<Boolean> =
        safeCall {
            auth.sendPasswordResetEmail(email).await()
            true
        }

    override suspend fun confirmPasswordReset(code: String, newPassword: String): ResultWrapper<Boolean> =
        safeCall {
            auth.confirmPasswordReset(code, newPassword).await()
            true
        }

    override fun getCurrentUser(): FirebaseUser? = auth.currentUser

    override fun logout() = auth.signOut()
}
