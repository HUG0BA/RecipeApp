package com.example.recipeapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.recipeapp.models.recipeDetailDummy
import com.example.recipeapp.ui.components.RecipeDetailsWithImage
import com.example.recipeapp.ui.theme.AppTheme

@Composable
fun RecipeDetailsScreen(modifier: Modifier = Modifier) {
    RecipeDetailsWithImage(
        recipeDetailModel = recipeDetailDummy
    )
}

@Preview
@Composable
private fun RecipeDetailsScreenPrev() {
    AppTheme {
        RecipeDetailsScreen()
    }
}