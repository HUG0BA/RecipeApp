package com.example.recipeapp.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.recipeapp.ui.components.MainBody
import com.example.recipeapp.ui.components.RecipeTopAppBar
import com.example.recipeapp.ui.theme.AppTheme

@Composable
fun MainScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { RecipeTopAppBar() }
    ) { innerPadding ->
        MainBody(modifier = Modifier.padding(innerPadding))
    }
}

@Preview
@Composable
private fun MainScreenPrev() {
    AppTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { RecipeTopAppBar() }
        ) { innerPadding ->
            MainBody(modifier = Modifier.padding(innerPadding))
        }
    }
}