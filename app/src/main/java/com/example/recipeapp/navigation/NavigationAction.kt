package com.example.recipeapp.navigation

import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder

sealed interface NavigationAction {
    data class Navigate(
        val destination: Destination,
        val navOptions: NavOptionsBuilder.() -> Unit = {}
    ):NavigationAction

    data object NavigateUp: NavigationAction
}