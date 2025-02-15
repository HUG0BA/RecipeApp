package com.example.recipeapp.events

sealed interface LoginEvent {
    data class SetEmail(val email: String): LoginEvent
    data class SetPassword(val password: String): LoginEvent
    object Submit: LoginEvent
    object SignUp: LoginEvent
}