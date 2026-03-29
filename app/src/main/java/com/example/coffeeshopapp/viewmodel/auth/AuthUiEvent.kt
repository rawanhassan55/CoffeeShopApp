package com.example.coffeeshopapp.viewmodel.auth

sealed class AuthUiEvent {
    data class ShowMessage(val message: String) : AuthUiEvent()
    data object NavigateToHome : AuthUiEvent()
    data object NavigateToVerifyEmail : AuthUiEvent()
    data object NavigateToOnboarding : AuthUiEvent()
    data object  NavigateToLogin : AuthUiEvent()
    data object NavigateBack : AuthUiEvent()
}
