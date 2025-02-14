package com.example.recipeapp.state

import android.net.Uri

data class AddRecipeState(
    var title: String = "Titulo",
    var description: String = "Descripcion",
    var preparationTime: Int = 0,
    var isFavorite: Boolean = false,
    var image: Uri? = null
)