package com.example.recipeapp.events

sealed interface AddRecipeScreenEvent {
    data class SetTitle(val title: Int): AddRecipeScreenEvent
    data class SetDescription(val description: String): AddRecipeScreenEvent
    data class SetPreparationTime(val preparationTime: Int): AddRecipeScreenEvent
    data class SetFavorite(val isFavorite: Boolean): AddRecipeScreenEvent
    data class SetImage(val image: String): AddRecipeScreenEvent
    object AddRecipe: AddRecipeScreenEvent
}