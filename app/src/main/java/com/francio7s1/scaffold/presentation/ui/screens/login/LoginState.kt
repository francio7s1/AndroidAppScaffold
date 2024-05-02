package com.francio7s1.scaffold.presentation.ui.screens.login

data class LoginState(
    val usernameInput: String = "",
    val passwordInput: String = "",
    val isInputValid: Boolean = false,
    val isPasswordShown: Boolean = false,
    val errorMessageInput: String? = null,
    val isLogged: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessageLoginProcess: String? = null
)
