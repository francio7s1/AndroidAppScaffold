package com.francio7s1.scaffold.domain.useCases.auth

import com.francio7s1.scaffold.domain.models.auth.LoginInputValidationType

class ValidateLoginInputUseCase() {
    operator fun invoke(
        username: String,
        password: String
    ): LoginInputValidationType {
        if (username.isEmpty() || password.isEmpty()) {
            return LoginInputValidationType.EMPTY_FIELD
        }
        if ("@" !in username) {
            return LoginInputValidationType.NO_VALID_USERNAME
        }
        return LoginInputValidationType.VALID
    }
}