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
import com.example.recipeapp.models.dummieChips
import com.example.recipeapp.models.recipeOutlineDummies
import com.example.recipeapp.room.RecipeEntity

@Composable
fun MainBody(modifier: Modifier = Modifier, recipes: List<RecipeEntity>, onEvent: (MainScreenEvent) -> Unit) {
    Column(modifier = modifier) {
        ChipFilterMenu(selectedFilter = Filters.NONE, onEvent = {})
        Spacer(modifier = Modifier.size(10.dp))
        Box(modifier = Modifier.fillMaxSize()){
            RecipesGrid(
                recipes = recipes,
                onCardClick = onEvent
            )
        }
    }
}