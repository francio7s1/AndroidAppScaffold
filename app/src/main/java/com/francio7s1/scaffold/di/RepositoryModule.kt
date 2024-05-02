package com.francio7s1.scaffold.di

import android.content.SharedPreferences
import com.francio7s1.scaffold.data.remote.services.AuthService
import com.francio7s1.scaffold.data.repositoriesImpl.AuthRepositoryImpl
import com.francio7s1.scaffold.domain.repositoriesInt.AuthRepositoryInt
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideAuthRepository(
        authService: AuthService,
        prefs: SharedPreferences
    ): AuthRepositoryInt {
        return AuthRepositoryImpl(authService, prefs)
    }
}