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
import com.example.recipeapp.events.MainScreenEvent
import com.example.recipeapp.models.Filters
import com.example.recipeapp.ui.theme.AppTheme

@Composable
fun ChipFilterMenu(modifier: Modifier = Modifier, selectedFilter: Filters, onEvent: (MainScreenEvent) -> Unit) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Filters.entries.drop(1).forEach {
            it -> FilterChip(
                selected = selectedFilter == it,
                onClick = { onEvent(MainScreenEvent.SelectFilter(it)) },
                label = {
                    Text(
                        text = it.description,
                        fontStyle = MaterialTheme.typography.labelMedium.fontStyle,
                        fontSize = MaterialTheme.typography.labelMedium.fontSize
                    ) }
            )
        }
    }
}



@Preview
@Composable
private fun ChipFilterMenuPrev() {
    AppTheme() {
        ChipFilterMenu(
            selectedFilter = Filters.PREPARATION_TIME_DESCENDING,
            onEvent = {}
        )
    }
}