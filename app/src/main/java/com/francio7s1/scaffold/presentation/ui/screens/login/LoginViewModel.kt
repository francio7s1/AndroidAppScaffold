package com.francio7s1.scaffold.presentation.ui.screens.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.francio7s1.scaffold.domain.models.auth.LoginInputValidationType
import com.francio7s1.scaffold.domain.useCases.auth.LoginUserUseCase
import com.francio7s1.scaffold.domain.useCases.auth.ValidateLoginInputUseCase
import com.francio7s1.scaffold.utils.network.AsyncResult
import com.francio7s1.scaffold.utils.network.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUserUseCase: LoginUserUseCase,
    private val validateLoginInputUseCase: ValidateLoginInputUseCase
): ViewModel() {

    private val _loginState = mutableStateOf(LoginState())
    val loginState: State<LoginState> = _loginState

    fun loginUser() = viewModelScope.launch {
        loginUserUseCase.invoke(
            username = _loginState.value.usernameInput,
            password = _loginState.value.passwordInput
        ).map {
            processLoginUserResponse(it)
        }.collect {
            loginUserResult(it)
        }
    }

    private fun processLoginUserResponse(
        loginUserResult: AsyncResult<Boolean>
    ): AsyncResult<Boolean> =
        when (loginUserResult.status) {
            Status.SUCCESS ->
                if (loginUserResult.data == null) {
                    AsyncResult(Status.ERROR, null, null)
                } else {
                    AsyncResult(Status.SUCCESS, loginUserResult.data, null)
                }

            Status.LOADING -> AsyncResult(Status.LOADING, null, null)
            Status.ERROR -> AsyncResult(Status.ERROR, null, loginUserResult.throwable)

        }


    private fun loginUserResult(
        loginUserResult: AsyncResult<Boolean>
    ) {
        when (loginUserResult.status) {
            Status.SUCCESS -> {
                _loginState.value = loginState.value.copy(
                    isLogged = loginUserResult.data == true,
                    isLoading = false
                )
            }
            Status.ERROR -> {
                _loginState.value = loginState.value.copy(
                    isLoading = false
                )
            }
            Status.LOADING -> {
                _loginState.value = loginState.value.copy(
                    isLoading = true
                )
            }
        }
    }

    fun onEmailInputChange(
        newValue: String
    ) {
        _loginState.value = loginState.value.copy(
            usernameInput = newValue
        )
    }

    fun onPasswordInputChange(
        newValue: String
    ) {
        _loginState.value = loginState.value.copy(
            passwordInput = newValue
        )
    }

    fun onToggleVisualTransformation() {
        _loginState.value = loginState.value.copy(
            isPasswordShown = !loginState.value.isPasswordShown
        )
    }

    private fun checkInputValidation() {
        val validationResult = validateLoginInputUseCase(
            _loginState.value.usernameInput,
            _loginState.value.passwordInput
        )
        processInputValidationType(validationResult)
    }

    private fun processInputValidationType(type: LoginInputValidationType) {
        when(type) {
            LoginInputValidationType.EMPTY_FIELD -> {
                _loginState.value = loginState.value.copy(
                    errorMessageInput = "Campos vacíos",
                    isInputValid = false
                )
            }
            LoginInputValidationType.NO_VALID_USERNAME -> {
                _loginState.value = loginState.value.copy(
                    errorMessageInput = "Email no válido",
                    isInputValid = false
                )
            }
            LoginInputValidationType.VALID -> {
                _loginState.value = loginState.value.copy(
                    errorMessageInput = null,
                    isInputValid = true
                )
            }
        }
    }

}