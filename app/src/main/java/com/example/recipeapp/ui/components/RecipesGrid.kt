package com.example.recipeapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
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
import coil3.compose.AsyncImage
import com.example.recipeapp.R
import com.example.recipeapp.events.MainScreenEvent
import com.example.recipeapp.room.RecipeEntity
import com.example.recipeapp.room.recipeDummies
import com.example.recipeapp.ui.theme.AppTheme

@Composable
fun RecipesGrid(modifier: Modifier = Modifier, recipes: List<RecipeEntity>, onCardClick: (MainScreenEvent) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        items(items = recipes){
            RecipeOutline(
                recipeEntity = it,
                onClick = { onCardClick(MainScreenEvent.SeeRecipeDetails(it.id)) }
            )
        }
    }
}

@Composable
fun RecipeOutline(modifier: Modifier = Modifier, recipeEntity: RecipeEntity, onClick: () -> Unit) {
    ElevatedCard(
        onClick = onClick,
        modifier = modifier.wrapContentWidth()
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if(recipeEntity.isFavorite){
                Icon(
                    modifier = Modifier.size(16.dp).align(Alignment.End),
                    imageVector = Icons.Filled.Star,
                    contentDescription = ""
                )
            }else{
                Spacer(modifier = Modifier.size(18.dp))
            }



            val imageModifier = Modifier.size(100.dp).clip(CircleShape)
            if(recipeEntity.image == null){
                Image(
                    modifier = imageModifier,
                    painter = painterResource(R.drawable.empty_plate),
                    contentScale = ContentScale.Crop,
                    contentDescription = ""
                )
            }else{
                AsyncImage(
                    modifier = imageModifier,
                    model = recipeEntity.image,
                    contentScale = ContentScale.Crop,
                    contentDescription = "Image from gallery"
                )
            }

            Spacer(modifier = Modifier.size(10.dp))

            Text(
                text = recipeEntity.title,
                fontStyle = MaterialTheme.typography.bodyLarge.fontStyle,
                fontSize = MaterialTheme.typography.bodySmall.fontSize
            )

            Text(
                text = recipeEntity.preparationTime.toString(),
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
            recipeEntity = RecipeEntity(0, "", "", 0, false),
            onClick = {}
        )
    }

}

@Preview
@Composable
private fun RecipesGridPrev() {
    AppTheme {
        RecipesGrid(
            recipes = recipeDummies,
            onCardClick = {}
        )
    }
}