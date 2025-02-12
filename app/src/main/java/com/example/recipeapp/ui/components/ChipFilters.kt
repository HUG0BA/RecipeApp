package com.example.recipeapp.ui.components


import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.recipeapp.models.FilterChipModel
import com.example.recipeapp.models.dummieChips
import com.example.recipeapp.ui.theme.AppTheme

@Composable
fun ChipFilterMenu(modifier: Modifier = Modifier, chips: List<FilterChipModel>) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        chips.forEach {
            it -> FilterChip(
                selected = it.isSelected,
                onClick = { it.onClick() },
                label = {
                    Text(
                        text = it.name,
                        fontStyle = MaterialTheme.typography.labelMedium.fontStyle,
                        fontSize = MaterialTheme.typography.labelMedium.fontSize
                    )
                }
            )
        }
    }
}



@Preview
@Composable
private fun ChipFilterMenuPrev() {
    AppTheme() {
        ChipFilterMenu(chips = dummieChips)
    }
}