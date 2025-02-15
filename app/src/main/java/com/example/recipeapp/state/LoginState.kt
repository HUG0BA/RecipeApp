package com.example.recipeapp.state

import androidx.compose.material3.SnackbarHostState

data class LoginState(
    val email: String = "",
    val password: String = ""
)