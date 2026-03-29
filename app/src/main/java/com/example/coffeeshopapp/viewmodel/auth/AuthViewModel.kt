package com.example.coffeeshopapp.viewmodel.auth

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeshopapp.data.model.UserModel
import com.example.coffeeshopapp.data.repository.firebase.auth.FirebaseAuthRepository
import com.example.coffeeshopapp.utils.UserDataStore
import com.example.coffeeshopapp.utils.result.ResultWrapper
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: FirebaseAuthRepository,
    private val userDataStore: UserDataStore
) : ViewModel() {

    // State
    private val _authState = MutableStateFlow(AuthUiState())
    val authState = _authState.asStateFlow()

    // Events
    private val _authEvent = MutableSharedFlow<AuthUiEvent>()
    val authEvent = _authEvent.asSharedFlow()

    private fun sendEvent(event: AuthUiEvent) {
        viewModelScope.launch { _authEvent.emit(event) }
    }

    private fun handleResultError(result: ResultWrapper.Error) {
        sendEvent(AuthUiEvent.ShowMessage(result.message))
    }

    // Splash
    fun checkAuthStatus() {
        viewModelScope.launch {

            val isOnboardingSeen = userDataStore.isOnboardingSeen().first()
            val user = repository.getCurrentUser()

            when {
                !isOnboardingSeen -> {
                    sendEvent(AuthUiEvent.NavigateToOnboarding)
                }

                user == null -> {
                    sendEvent(AuthUiEvent.NavigateToLogin)
                }

                user.isEmailVerified -> {
                    sendEvent(AuthUiEvent.NavigateToHome)
                }

                else -> {
                    repository.logout()
                    sendEvent(AuthUiEvent.NavigateToLogin)
                }
            }
        }
    }

    // Sign Up
    fun signUp(name: String, email: String, password: String) {
        if (!validateCredentials(email, password)) return

        viewModelScope.launch {
            _authState.value = _authState.value.copy(isLoading = true)
            try {
                val result = withContext(Dispatchers.IO) { repository.signUp(email, password) }

                when (result) {
                    is ResultWrapper.Success -> handleSignUpSuccess(result.data, name, email)
                    is ResultWrapper.Error -> handleResultError(result)
                }

            } finally {
                _authState.value = _authState.value.copy(isLoading = false)
            }
        }
    }

    private suspend fun handleSignUpSuccess(user: FirebaseUser?, name: String, email: String) {
        if (user == null) {
            sendEvent(AuthUiEvent.ShowMessage("Failed to create account"))
            return
        }

        // Save user locally
        withContext(Dispatchers.IO) {
            userDataStore.saveUser(
                UserModel(
                    name = name,
                    email = email,
                    phone = "",
                    birthday = "",
                    image = ""
                )
            )
        }

        // Send verification email
        when (val verifyResult = repository.sendEmailVerification(user)) {
            is ResultWrapper.Success -> {
                sendEvent(AuthUiEvent.ShowMessage("Verification email sent. Check your inbox."))
                sendEvent(AuthUiEvent.NavigateToVerifyEmail)
            }
            is ResultWrapper.Error -> handleResultError(verifyResult)
        }
    }

    // Login
    fun login(email: String, password: String) {
        if (!validateCredentials(email, password)) return

        viewModelScope.launch {
            _authState.value = _authState.value.copy(isLoading = true)
            try {
                val result = withContext(Dispatchers.IO) { repository.login(email, password) }

                when (result) {
                    is ResultWrapper.Success -> handleLoginSuccess(result.data)
                    is ResultWrapper.Error -> handleResultError(result)
                }

            } finally {
                _authState.value = _authState.value.copy(isLoading = false)
            }
        }
    }

    private fun handleLoginSuccess(user: FirebaseUser?) {
        if (user == null) {
            sendEvent(AuthUiEvent.ShowMessage("Login failed"))
            return
        }

        if (user.isEmailVerified) {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    userDataStore.saveUser(
                        UserModel(
                            name = user.displayName ?: "",
                            email = user.email ?: "",
                            phone = "",
                            birthday = "",
                            image = user.photoUrl?.toString() ?: ""
                        )
                    )
                }
                _authState.value = _authState.value.copy(user = user, isEmailVerified = true)
                sendEvent(AuthUiEvent.NavigateToHome)
            }
        } else {
            repository.logout()
            sendEvent(AuthUiEvent.NavigateToVerifyEmail)
        }
    }

    // Verify Email
    fun reloadUser() {
        viewModelScope.launch {
            _authState.value = _authState.value.copy(isLoading = true)
            try {
                val user = repository.getCurrentUser()
                if (user == null) {
                    sendEvent(AuthUiEvent.ShowMessage("No logged in user"))
                    return@launch
                }

                user.reload().await()
                val updatedUser = repository.getCurrentUser()
                val verified = updatedUser?.isEmailVerified == true

                _authState.value = _authState.value.copy(user = updatedUser, isEmailVerified = verified)
                if (verified) sendEvent(AuthUiEvent.NavigateToHome)
                else sendEvent(AuthUiEvent.ShowMessage("Email not verified yet"))

            } catch (e: Exception) {
                sendEvent(AuthUiEvent.ShowMessage(e.message ?: "Failed to check email"))
            } finally {
                _authState.value = _authState.value.copy(isLoading = false)
            }
        }
    }

    // Reset Password
    fun resetPassword(email: String) {
        if (email.isBlank()) {
            sendEvent(AuthUiEvent.ShowMessage("Email cannot be empty"))
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            sendEvent(AuthUiEvent.ShowMessage("Invalid email format"))
            return
        }

        viewModelScope.launch {
            _authState.value = _authState.value.copy(isLoading = true)
            try {
                val result = withContext(Dispatchers.IO) { repository.resetPassword(email) }
                when (result) {
                    is ResultWrapper.Success -> sendEvent(AuthUiEvent.ShowMessage("Password reset email sent"))
                    is ResultWrapper.Error -> handleResultError(result)
                }
            } finally {
                _authState.value = _authState.value.copy(isLoading = false)
            }
        }
    }

    //Resend Verification
    fun resendVerificationEmail() {
        val user = repository.getCurrentUser()
        if (user == null) {
            sendEvent(AuthUiEvent.ShowMessage("No logged in user"))
            return
        }

        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) { repository.sendEmailVerification(user) }
            when (result) {
                is ResultWrapper.Success -> sendEvent(AuthUiEvent.ShowMessage("Verification email sent again"))
                is ResultWrapper.Error -> handleResultError(result)
            }
        }
    }

    // Logout
    fun logout() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) { userDataStore.clearUser() }
            repository.logout()
            _authState.value = AuthUiState()  // reset state
            sendEvent(AuthUiEvent.NavigateToLogin)
        }
    }

    // Validation
    private fun validateCredentials(email: String, password: String): Boolean {
        return when {
            email.isBlank() || password.isBlank() -> {
                sendEvent(AuthUiEvent.ShowMessage("Email and password cannot be empty"))
                false
            }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                sendEvent(AuthUiEvent.ShowMessage("Invalid email format"))
                false
            }
            password.length < 6 -> {
                sendEvent(AuthUiEvent.ShowMessage("Password must be at least 6 characters"))
                false
            }
            else -> true
        }
    }
}