package com.francio7s1.scaffold.data.remote.services

import com.francio7s1.scaffold.data.remote.dto.requests.LoginUserRequest
import com.francio7s1.scaffold.data.remote.dto.responses.LoginUserResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthService {
    @Headers("Content-Type: application/json")
    @POST("auth/login")
    suspend fun loginUser(
        @Body loginUserRequest: LoginUserRequest
    ): LoginUserResponse
}