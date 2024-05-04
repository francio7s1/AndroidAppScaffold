package com.francio7s1.scaffold

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.francio7s1.scaffold.presentation.ui.screens.login.LoginScreen
import com.francio7s1.scaffold.presentation.ui.theme.ScaffoldTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScaffoldTheme {
                LoginScreen()
            }
        }
    }
}