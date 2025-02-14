package com.example.recipeapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.recipeapp.events.RecipeDetailsEvent
import com.example.recipeapp.room.RecipeEntity
import com.example.recipeapp.room.recipeDummy
import com.example.recipeapp.ui.components.RecipeDetailsWithImage
import com.example.recipeapp.ui.theme.AppTheme

@Composable
fun RecipeDetailsScreen(modifier: Modifier = Modifier, recipe: RecipeEntity, onEvent: (RecipeDetailsEvent) -> Unit) {
    RecipeDetailsWithImage(
        recipe = recipe,
        onEvent = onEvent
    )
}

@Preview
@Composable
private fun RecipeDetailsScreenPrev() {
    AppTheme {
        RecipeDetailsScreen(
            recipe = recipeDummy,
            onEvent = {}
        )
    }
}