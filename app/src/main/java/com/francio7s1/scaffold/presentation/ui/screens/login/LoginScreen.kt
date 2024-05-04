package com.francio7s1.scaffold.presentation.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.RemoveRedEye
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.francio7s1.scaffold.R
import com.francio7s1.scaffold.presentation.ui.components.TextEntryModule

@Composable
fun LoginScreen() {

    val viewModel = hiltViewModel<LoginViewModel>()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (viewModel.loginState.value.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.width(25.dp),
                color = Color.DarkGray,
                trackColor = Color.LightGray
            )
        } else {
            LoginContainer(
                usernameValue = { viewModel.loginState.value.usernameInput },
                passwordValue = { viewModel.loginState.value.passwordInput },
                onEmailChanged = viewModel::onEmailInputChange,
                onPasswordChanged = viewModel::onPasswordInputChange,
                isPasswordShown = {
                    viewModel.loginState.value.isPasswordShown
                },
                onTrailingPasswordIconClick = viewModel::onToggleVisualTransformation,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray, RoundedCornerShape(15.dp))
                    .padding(10.dp, 15.dp, 10.dp, 15.dp)
            )
            Button(
                onClick = {
                    viewModel.loginUser()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (viewModel.loginState.value.isLogged) {
                        Color.Green
                    } else {
                        Color.Red
                    }
                )
            ) {
                Text(
                    text = stringResource(id = R.string.login_button)
                )
            }
        }
    }
}

@Composable
fun LoginContainer(
    usernameValue:() -> String,
    passwordValue:()-> String,
    onEmailChanged:(String) -> Unit,
    onPasswordChanged:(String) -> Unit,
    isPasswordShown:()->Boolean,
    onTrailingPasswordIconClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ){
        TextEntryModule(
            modifier = Modifier
                .fillMaxWidth(),
            description = stringResource(id = R.string.login_username_description),
            hint = stringResource(id = R.string.login_username_hint),
            textValue = usernameValue(),
            textColor = Color.Black,
            cursorColor = Color.Black,
            onValueChanged = onEmailChanged,
            trailingIcon = null,
            onTrailingIconClick = null,
            leadingIcon = Icons.Outlined.Email
        )
        TextEntryModule(
            modifier = Modifier
                .fillMaxWidth(),
            description = stringResource(id = R.string.login_password_description),
            hint = stringResource(id = R.string.login_password_hint),
            textValue = passwordValue(),
            textColor = Color.Black,
            cursorColor = Color.Black,
            onValueChanged = onPasswordChanged,
            trailingIcon = Icons.Filled.RemoveRedEye,
            onTrailingIconClick = {
                onTrailingPasswordIconClick()
            },
            leadingIcon = Icons.Filled.Key,
            visualTransformation = if(isPasswordShown()){
                VisualTransformation.None
            } else PasswordVisualTransformation(),
            keyboardType = KeyboardType.Password
        )
    }
}