package com.francio7s1.scaffold.presentation.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LoginScreen() {

    val viewModel = hiltViewModel<LoginViewModel>()

    Column {
        LoginContainer(
            usernameValue = { viewModel.loginState.value.usernameInput },
            passwordValue = { viewModel.loginState.value.passwordInput },
            onEmailChanged = viewModel::onEmailInputChange,
            onPasswordChanged = viewModel::onPasswordInputChange,
            onLoginButtonClick = viewModel::loginUser
        )
    }
}

@Composable
fun LoginContainer(
    usernameValue: () -> String,
    passwordValue: () -> String,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        TextField(
            value = usernameValue(),
            onValueChange = onEmailChanged,
            supportingText = {Text(text = "Usuario")}
        )
        TextField(
            value = passwordValue(),
            onValueChange = onPasswordChanged,
            supportingText = {Text(text = "Password")}
        )
        Button(onClick = { onLoginButtonClick() }) {
            
        }
    }
}