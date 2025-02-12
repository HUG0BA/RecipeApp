package com.example.recipeapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipeapp.R
import com.example.recipeapp.models.RecipeOutlineModel
import com.example.recipeapp.models.recipeOutlineDummies
import com.example.recipeapp.ui.theme.AppTheme

@Composable
fun RecipesGrid(modifier: Modifier = Modifier,recipes: List<RecipeOutlineModel>, onCardClick: () -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        items(items = recipes){
            RecipeOutline(
                recipeOutlineModel = it,
                onClick = onCardClick
            )
        }
    }
}

@Composable
fun RecipeOutline(modifier: Modifier = Modifier, recipeOutlineModel: RecipeOutlineModel, onClick: () -> Unit) {
    ElevatedCard(
        onClick = onClick,
        modifier = modifier.wrapContentSize()
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if(recipeOutlineModel.isFavorite){
                Icon(
                    modifier = Modifier.size(16.dp).align(Alignment.End),
                    imageVector = Icons.Filled.Star,
                    contentDescription = ""
                )
            }else{
                Spacer(modifier = Modifier.size(18.dp))
            }


            Image(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                painter = painterResource(R.drawable.ic_launcher_background),
                contentScale = ContentScale.Fit,
                contentDescription = "An image"
            )

            Spacer(modifier = Modifier.size(10.dp))

            Text(
                text = recipeOutlineModel.title,
                fontStyle = MaterialTheme.typography.bodyLarge.fontStyle,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize
            )

            Text(
                text = recipeOutlineModel.preparationTime.toString(),
                fontStyle = MaterialTheme.typography.bodySmall.fontStyle,
                fontSize = MaterialTheme.typography.bodySmall.fontSize
            )
        }
    }
}


@Preview
@Composable
private fun RecipeOutlinePrev() {
    AppTheme {
        RecipeOutline(
            recipeOutlineModel =  recipeOutlineDummies[0],
            onClick = {}
        )
    }

}

@Preview
@Composable
private fun RecipesGridPrev() {
    AppTheme {
        RecipesGrid(
            recipes = recipeOutlineDummies,
            onCardClick = {}
        )
    }
}