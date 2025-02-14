package com.example.recipeapp.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.recipeapp.events.MainScreenEvent
import com.example.recipeapp.models.Filters
import com.example.recipeapp.room.RecipeEntity
import com.example.recipeapp.state.MainScreenState

@Composable
fun MainBody(modifier: Modifier = Modifier, mainScreenState: MainScreenState, onEvent: (MainScreenEvent) -> Unit) {
    Column(modifier = modifier) {
        ChipFilterMenu(selectedFilter = mainScreenState.filter, onEvent = onEvent)
        Spacer(modifier = Modifier.size(10.dp))
        Box(modifier = Modifier.fillMaxSize()){
            RecipesGrid(
                recipes = mainScreenState.recipes,
                onCardClick = onEvent
            )
        }
    }
}