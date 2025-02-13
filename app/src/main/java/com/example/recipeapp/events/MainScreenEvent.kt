package com.example.recipeapp.events

import com.example.recipeapp.models.Filters

sealed interface MainScreenEvent{
    data class SeeRecipeDetails(val recipeId: Int): MainScreenEvent
    data class SelectFilter(val filter: Filters): MainScreenEvent
    object AddRecipe: MainScreenEvent
}