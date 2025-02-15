package com.example.recipeapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipeapp.events.LoginEvent
import com.example.recipeapp.state.LoginState
import com.example.recipeapp.ui.theme.AppTheme

@Composable
fun LoginScreen(modifier: Modifier = Modifier, loginState: LoginState, onEvent: (LoginEvent) -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = loginState.email,
                onValueChange = { onEvent(LoginEvent.SetEmail(it)) },
                placeholder = {
                    Text("Email")
                }
            )
            Spacer(Modifier.size(8.dp))
            TextField(
                value = loginState.password,
                onValueChange = { onEvent(LoginEvent.SetPassword(it))},
                placeholder = {
                    Text("Contrasena")
                }
            )
            Spacer(modifier =  Modifier.size(32.dp))
            Button(
                onClick = { onEvent(LoginEvent.Submit)}
            ) {
                Text("Iniciar Sesion")
            }
            Spacer(modifier = Modifier.size(128.dp))
            Text(text = "¿Eres nuevo por aqui?")
            Button(
                onClick = { onEvent(LoginEvent.SignUp)}
            ) {
                Text("Crea una cuenta")
            }
        }
    }
}

@Preview
@Composable
private fun LoginScreenPrev() {
    AppTheme {
        LoginScreen(
            loginState = LoginState(),
            onEvent =  {}
        )
    }
}