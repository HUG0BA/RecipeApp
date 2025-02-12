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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipeapp.R
import com.example.recipeapp.models.RecipeDetailModel
import com.example.recipeapp.models.recipeDetailDummy
import com.example.recipeapp.ui.theme.AppTheme

val horizontalTextPadding = 45.dp

@Composable
fun RecipeDetailsWithImage(
    modifier: Modifier = Modifier,
    recipeDetailModel: RecipeDetailModel
) {
    RecipeDetailsWithImage(
        modifier = modifier,
        header = {
            Text(
                text = recipeDetailModel.title,
                fontStyle = MaterialTheme.typography.displayLarge.fontStyle,
                fontSize = MaterialTheme.typography.displayLarge.fontSize
            )
        },
        description = {
            Text(
                text = recipeDetailModel.description,
                fontStyle = MaterialTheme.typography.bodyMedium.fontStyle,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize
            )
        },
        isFavorite = recipeDetailModel.isFavorite
    )
}

@Composable
fun AddRecipeWithImage(modifier: Modifier = Modifier) {
    var title by remember { mutableStateOf("HIHIHIH") }
    var description by remember { mutableStateOf("JJ") }

    RecipeDetailsWithImage(
        modifier = modifier,
        header = {
            BasicTextField(
                value = title,
                onValueChange = { title = it },
                textStyle = MaterialTheme.typography.displayLarge
            )
        },
        description = {
            BasicTextField(
                value = description,
                onValueChange = { description = it },
                textStyle = MaterialTheme.typography.bodyMedium
            )
        },
        isFavorite = false
    )
}

@Composable
private fun RecipeDetailsWithImage(
    modifier: Modifier = Modifier,
    header: @Composable () -> Unit,
    description: @Composable () -> Unit,
    isFavorite: Boolean
) {
    BoxWithConstraints(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier.background(MaterialTheme.colorScheme.surface)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(.35f)
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(
                        start = horizontalTextPadding,
                        top = 50.dp,
                        end = horizontalTextPadding
                    )
            ) {
                header()
            }

            Spacer(modifier = Modifier.weight(.20f))

            Box(
                modifier = Modifier
                    .weight(.45f)
                    .padding(horizontal = horizontalTextPadding)
            ){
                description()
            }
        }

        Image(
            modifier = Modifier
                .size(maxWidth * .6f)
                .offset(x = -maxWidth * .05f, y = -maxHeight * .15f)
                .clip(CircleShape),
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = ""
        )

        Icon(
            modifier = Modifier
                .size(55.dp)
                .offset(x = maxWidth * .35f, y = -maxHeight * 0.25f)
            ,
            imageVector = if(isFavorite) Icons.Filled.Star else Icons.Outlined.Star,
            contentDescription = "Favorite",
            tint = if(isFavorite) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.onPrimary
        )
    }
}


@Preview
@Composable
private fun RecipeDetailsPrev() {
    AppTheme {
        Box(modifier = Modifier.fillMaxSize()){
            RecipeDetailsWithImage(
                recipeDetailModel = recipeDetailDummy
            )
        }
    }
}

@Preview
@Composable
private fun AddRecipeWithImagePrev() {
    AppTheme {
        AddRecipeWithImage()
    }
}

