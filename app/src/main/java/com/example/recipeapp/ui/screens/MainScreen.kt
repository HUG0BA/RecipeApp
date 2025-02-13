package com.example.recipeapp.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.recipeapp.events.MainScreenEvent
import com.example.recipeapp.state.MainScreenState
import com.example.recipeapp.ui.components.MainBody
import com.example.recipeapp.ui.components.RecipeTopAppBar
import com.example.recipeapp.ui.theme.AppTheme

@Composable
fun MainScreen(mainScreenState: MainScreenState, onEvent: (MainScreenEvent) -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { RecipeTopAppBar() },
        floatingActionButton = {
            FloatingActionButton(onClick = { onEvent(MainScreenEvent.AddRecipe) } ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Recipe")
            }
        }
    ) { innerPadding ->
        MainBody(
            modifier = Modifier.padding(innerPadding),
            onEvent = onEvent,
            recipes = mainScreenState.recipes
        )
    }
}

@Preview
@Composable
private fun MainScreenPrev() {
    AppTheme {
        MainScreen(
            mainScreenState = MainScreenState(),
            onEvent = {}
        )
    }
}