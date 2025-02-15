package com.example.recipeapp.navigation

import kotlinx.serialization.Serializable

sealed interface Destination {
    @Serializable
    object SplashScreen: Destination

    @Serializable
    object LoginScreen: Destination

    @Serializable
    object SignUpScreen: Destination

    @Serializable
    object MainScreen: Destination

    @Serializable
    object AddRecipeScreen: Destination

    @Serializable
    data class RecipeDetailsScreen(val id: Int): Destination
}