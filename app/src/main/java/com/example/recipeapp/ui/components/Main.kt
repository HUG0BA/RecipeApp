package com.example.recipeapp.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.recipeapp.models.dummieChips
import com.example.recipeapp.models.recipeOutlineDummies

@Composable
fun MainBody(modifier: Modifier = Modifier, onCardClick: () -> Unit) {
    Column(modifier = modifier) {
        ChipFilterMenu(chips = dummieChips)
        Spacer(modifier = Modifier.size(10.dp))
        Box(modifier = Modifier.fillMaxSize()){
            RecipesGrid(
                recipes = recipeOutlineDummies,
                onCardClick = onCardClick
            )
        }
    }
}