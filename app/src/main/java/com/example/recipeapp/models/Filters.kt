package com.example.recipeapp.models

enum class Filters(val description: String) {
    NONE(""),
    FAVORITE("Favoritas"),
    PREPARATION_TIME_DESCENDING("Tiempo de preparacion")
}