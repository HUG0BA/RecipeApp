package com.example.recipeapp.state

data class AddRecipeState(
    var title: String = "Titulo",
    var description: String = "Descripcion",
    var preparationTime: Int = 0,
    var isFavorite: Boolean = false,
    var image: String = ""
)