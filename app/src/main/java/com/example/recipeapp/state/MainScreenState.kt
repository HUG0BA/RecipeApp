package com.example.recipeapp.state

import com.example.recipeapp.models.Filters
import com.example.recipeapp.room.RecipeEntity

data class MainScreenState(
    val recipes: List<RecipeEntity> = emptyList(),
    val filter: Filters = Filters.NONE
)