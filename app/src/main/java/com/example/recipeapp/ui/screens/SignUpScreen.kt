package com.example.recipeapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipeapp.events.LoginEvent
import com.example.recipeapp.events.SignUpEvent
import com.example.recipeapp.state.LoginState
import com.example.recipeapp.state.SignUpState
import com.example.recipeapp.ui.theme.AppTheme

@Composable
fun SignUpScreen(modifier: Modifier = Modifier, signUpState: SignUpState, onEvent: (SignUpEvent) -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = signUpState.user,
                onValueChange = { onEvent(SignUpEvent.SetUser(it)) },
                placeholder = {
                    Text("Usuario")
                }
            )
            Spacer(Modifier.size(8.dp))
            TextField(
                value = signUpState.email,
                onValueChange = { onEvent(SignUpEvent.SetEmail(it)) },
                placeholder = {
                    Text("Email")
                }
            )
            Spacer(Modifier.size(8.dp))
            TextField(
                value = signUpState.password,
                onValueChange = { onEvent(SignUpEvent.SetPassword(it))},
                placeholder = {
                    Text("Contrasena")
                }
            )
            Spacer(Modifier.size(8.dp))
            TextField(
                value = signUpState.confirmPassword,
                onValueChange = { onEvent(SignUpEvent.SetConfirmPassword(it))},
                placeholder = {
                    Text("Contrasena")
                }
            )
            Spacer(modifier =  Modifier.size(32.dp))
            Button(
                onClick = { onEvent(SignUpEvent.Submit)}
            ) {
                Text("Crear cuenta")
            }

        }
    }
}

@Preview
@Composable
private fun SignUpScreenPrev() {
    AppTheme{
        SignUpScreen(
          signUpState = SignUpState(),
            onEvent = {}
        )
    }
}