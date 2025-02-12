package com.example.recipeapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.recipeapp.ui.components.AddRecipeWithImage
import com.example.recipeapp.ui.theme.AppTheme

@Composable
fun AddRecipeScreen(modifier: Modifier = Modifier) {
    AddRecipeWithImage()
}

@Preview
@Composable
private fun AddRecipeScreenPrev() {
    AppTheme {
        AddRecipeScreen()
    }
}