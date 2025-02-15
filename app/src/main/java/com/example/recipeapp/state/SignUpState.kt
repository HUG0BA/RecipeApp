package com.example.recipeapp.state

data class SignUpState(
    val user: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = ""
)