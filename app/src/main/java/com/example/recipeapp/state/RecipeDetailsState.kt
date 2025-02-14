package com.example.recipeapp.state

import com.example.recipeapp.room.RecipeEntity

data class RecipeDetailsState(
    val recipe: RecipeEntity = RecipeEntity(1, "", "", 0, false)
)