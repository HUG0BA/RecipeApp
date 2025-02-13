package com.example.recipeapp.events


sealed interface RecipeDetailsEvent {
    object IsFavorite: RecipeDetailsEvent
}