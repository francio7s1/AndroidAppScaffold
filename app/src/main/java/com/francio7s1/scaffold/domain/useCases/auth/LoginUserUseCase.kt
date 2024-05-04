package com.francio7s1.scaffold.domain.useCases.auth

import com.francio7s1.scaffold.data.remote.dto.requests.LoginUserRequest
import com.francio7s1.scaffold.domain.repositoriesInt.AuthRepositoryInt
import com.francio7s1.scaffold.utils.network.AsyncResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(
    private val authRepository: AuthRepositoryInt
) {
    suspend operator fun invoke(
        username: String,
        password: String
    ): Flow<AsyncResult<Boolean>> =
        authRepository.loginUser(
            LoginUserRequest(
                username = username,
                password = password
            )
        )
}