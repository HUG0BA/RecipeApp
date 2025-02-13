package com.example.recipeapp.state

data class AddRecipeState(
    val title: String = "",
    val description: String = "",
    val preparationTime: Int,
    val isFavorite: Boolean = false,
    val image: String = ""
)