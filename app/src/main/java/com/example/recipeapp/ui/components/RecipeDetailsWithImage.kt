package com.example.recipeapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.recipeapp.R
import com.example.recipeapp.events.RecipeDetailsEvent
import com.example.recipeapp.room.RecipeEntity
import com.example.recipeapp.room.recipeDummy
import com.example.recipeapp.ui.theme.AppTheme

val horizontalTextPadding = 45.dp

@Composable
fun RecipeDetailsWithImage(
    modifier: Modifier = Modifier,
    recipe: RecipeEntity,
    onEvent: (RecipeDetailsEvent) -> Unit
) {
    BoxWithConstraints(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(.35f),
                color = MaterialTheme.colorScheme.primary
            ) {
                Text(
                    modifier = Modifier.padding(start = horizontalTextPadding, top = 50.dp, end = horizontalTextPadding),
                    text = recipe.title,
                    fontStyle = MaterialTheme.typography.displayLarge.fontStyle,
                    fontSize = MaterialTheme.typography.displayLarge.fontSize
                )
            }

            Spacer(modifier = Modifier.weight(.16f))

            Box(
                modifier = Modifier
                    .weight(.45f)
                    .padding(horizontal = horizontalTextPadding)
            ){
                LazyColumn {
                    item{
                        Text(
                            text = "Tiempo de preparacion: ${recipe.preparationTime} minutos",
                            fontStyle = MaterialTheme.typography.bodyMedium.fontStyle,
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    item{
                        Spacer(modifier = Modifier.size(16.dp))
                    }

                    item{
                        Text(
                            text = recipe.description,
                            fontStyle = MaterialTheme.typography.bodyMedium.fontStyle,
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize
                        )
                    }
                }
            }
        }

        val imageModifier = Modifier
            .size(maxWidth * .6f)
            .offset(x = -maxWidth * .05f, y = -maxHeight * .15f)
            .clip(CircleShape)

        if(recipe.image == null){
            Image(
                modifier = imageModifier,
                painter = painterResource(R.drawable.empty_plate),
                contentScale = ContentScale.Crop,
                contentDescription = ""
            )
        }else{
            AsyncImage(
                modifier = imageModifier,
                model = recipe.image,
                contentScale = ContentScale.Crop,
                contentDescription = "Image from gallery"
            )
        }

        IconButton(
            modifier = Modifier
                .offset(x = maxWidth * .35f, y = -maxHeight * 0.25f),
            onClick = {
                onEvent(RecipeDetailsEvent.IsFavorite)
            }
        ) {
            Icon(
                modifier = Modifier.size(64.dp),
                imageVector = Icons.Filled.Star,
                contentDescription = "Favorite",
                tint = if(recipe.isFavorite)  MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}


@Preview
@Composable
private fun RecipeDetailsPrev() {
    AppTheme {
        Box(modifier = Modifier.fillMaxSize()){
            RecipeDetailsWithImage(
                recipe = recipeDummy,
                onEvent = {}
            )
        }
    }
}


