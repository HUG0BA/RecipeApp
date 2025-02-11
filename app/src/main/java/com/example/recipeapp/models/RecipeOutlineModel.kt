package com.example.recipeapp.models

data class RecipeOutlineModel(
    val image: String,
    val title: String,
    val preparationTime: Int,
    val isFavorite: Boolean
)