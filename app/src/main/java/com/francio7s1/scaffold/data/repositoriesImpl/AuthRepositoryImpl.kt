package com.francio7s1.scaffold.data.repositoriesImpl

import android.content.SharedPreferences
import com.francio7s1.scaffold.data.remote.dto.requests.LoginUserRequest
import com.francio7s1.scaffold.data.remote.services.AuthService
import com.francio7s1.scaffold.domain.repositoriesInt.AuthRepositoryInt
import com.francio7s1.scaffold.utils.StoreConstants
import com.francio7s1.scaffold.utils.extensions.get
import com.francio7s1.scaffold.utils.extensions.set
import com.francio7s1.scaffold.utils.network.AsyncResult
import com.francio7s1.scaffold.utils.network.networkBoundResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService,
    private val prefs: SharedPreferences
) : AuthRepositoryInt {
    override suspend fun loginUser(loginUserRequest: LoginUserRequest): Flow<AsyncResult<Boolean>> =
        networkBoundResource(
            query = {
                flow {
                    emit(!prefs[StoreConstants.token, ""].isNullOrEmpty())
                }
            },
            fetch = {
                authService.loginUser(
                    loginUserRequest
                )
            },
            saveFetchResult = { loginUserResponse ->
                prefs[StoreConstants.token] = loginUserResponse.token
            }
        )

}