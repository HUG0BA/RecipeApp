package com.example.recipeapp.events

sealed interface AddRecipeScreenEvent {
    data class SetTitle(val title: String): AddRecipeScreenEvent
    data class SetDescription(val description: String): AddRecipeScreenEvent
    data class SetPreparationTime(val preparationTime: Int): AddRecipeScreenEvent
    object SetFavorite: AddRecipeScreenEvent
    data class SetImage(val image: String): AddRecipeScreenEvent
    object AddRecipe: AddRecipeScreenEvent
}