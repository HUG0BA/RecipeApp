package com.example.recipeapp.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.recipeapp.events.AddRecipeScreenEvent
import com.example.recipeapp.events.MainScreenEvent
import com.example.recipeapp.state.AddRecipeState
import com.example.recipeapp.ui.components.AddRecipeWithImage
import com.example.recipeapp.ui.theme.AppTheme

@Composable
fun AddRecipeScreen(
    modifier: Modifier = Modifier,
    addRecipeState: AddRecipeState,
    onEvent: (AddRecipeScreenEvent) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onEvent(AddRecipeScreenEvent.AddRecipe) }
            ) {
                Icon(imageVector = Icons.Filled.Done, contentDescription = "Done")
            }
        }
    ) { innerPadding ->
        AddRecipeWithImage(
            modifier = Modifier.padding(innerPadding),
            addRecipeState = addRecipeState,
            onEvent = onEvent
        )
    }

}

@Preview
@Composable
private fun AddRecipeScreenPrev() {
    AppTheme {
        AddRecipeScreen(
            addRecipeState = AddRecipeState(),
            onEvent = {}
        )
    }
}