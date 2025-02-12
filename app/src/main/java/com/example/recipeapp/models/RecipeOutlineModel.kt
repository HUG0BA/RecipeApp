package com.example.recipeapp.models

data class RecipeOutlineModel(
    val image: String,
    val title: String,
    val preparationTime: Int,
    val isFavorite: Boolean
)

val recipeOutlineDummies = listOf(
    RecipeOutlineModel("","Heart Pizza", 25, true),
    RecipeOutlineModel("","Sweet Pizza", 15, false),
    RecipeOutlineModel("","Sad Pizza", 35, false),
    RecipeOutlineModel("","Funny Pizza", 95, true),
    RecipeOutlineModel("","Crispy Pizza", 75, true)
)