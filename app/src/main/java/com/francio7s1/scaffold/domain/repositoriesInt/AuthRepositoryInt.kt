package com.francio7s1.scaffold.domain.repositoriesInt

import com.francio7s1.scaffold.data.remote.dto.requests.LoginUserRequest
import com.francio7s1.scaffold.utils.network.AsyncResult
import kotlinx.coroutines.flow.Flow

interface AuthRepositoryInt {
    suspend fun loginUser(
        loginUserRequest: LoginUserRequest
    ): Flow<AsyncResult<Boolean>>
}