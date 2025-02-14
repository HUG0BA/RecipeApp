package com.example.recipeapp.events

import android.net.Uri

sealed interface AddRecipeScreenEvent {
    data class SetTitle(val title: String): AddRecipeScreenEvent
    data class SetDescription(val description: String): AddRecipeScreenEvent
    data class SetPreparationTime(val preparationTime: Int): AddRecipeScreenEvent
    object IsFavorite: AddRecipeScreenEvent
    data class SetImage(val image: Uri?): AddRecipeScreenEvent
    object AddRecipe: AddRecipeScreenEvent
}