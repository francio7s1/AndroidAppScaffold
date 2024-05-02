package com.francio7s1.scaffold.di

import com.francio7s1.scaffold.domain.repositoriesInt.AuthRepositoryInt
import com.francio7s1.scaffold.domain.useCases.auth.LoginUserUseCase
import com.francio7s1.scaffold.domain.useCases.auth.ValidateLoginInputUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideLoginUserUseCase(
        authRepository: AuthRepositoryInt
    ): LoginUserUseCase {
        return LoginUserUseCase(
            authRepository
        )
    }

    @Provides
    @Singleton
    fun provideValidateLoginInputUseCase(): ValidateLoginInputUseCase {
        return ValidateLoginInputUseCase()
    }
}