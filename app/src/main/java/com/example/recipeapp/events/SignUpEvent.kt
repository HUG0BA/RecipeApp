package com.example.recipeapp.events

sealed interface SignUpEvent {
    data class SetUser(val user: String): SignUpEvent
    data class SetEmail(val email: String): SignUpEvent
    data class SetPassword(val password: String): SignUpEvent
    data class SetConfirmPassword(val confirmPassword: String): SignUpEvent
    object Submit: SignUpEvent
}