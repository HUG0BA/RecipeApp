package com.example.recipeapp.ui.components

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.recipeapp.R
import com.example.recipeapp.events.AddRecipeScreenEvent
import com.example.recipeapp.state.AddRecipeState
import com.example.recipeapp.ui.theme.AppTheme


@Composable
fun AddRecipeWithImage(
    modifier: Modifier = Modifier,
    addRecipeState: AddRecipeState,
    onEvent: (AddRecipeScreenEvent) -> Unit
) {
    val context = LocalContext.current
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            if(it != null){

                val flag = Intent.FLAG_GRANT_READ_URI_PERMISSION
                context.contentResolver.takePersistableUriPermission(it, flag)
            }
            onEvent(AddRecipeScreenEvent.SetImage(it))
        }
    )


    BoxWithConstraints(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier.background(MaterialTheme.colorScheme.surface)
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(.35f),
                color = MaterialTheme.colorScheme.primary
            ) {
                BasicTextField(
                    modifier = Modifier.padding(start = horizontalTextPadding, top = 50.dp, end = horizontalTextPadding),
                    value = addRecipeState.title,
                    onValueChange = { onEvent(AddRecipeScreenEvent.SetTitle(it)) },
                    textStyle = MaterialTheme.typography.displayLarge
                )
            }

            Spacer(modifier = Modifier.weight(.16f))

            Box(
                modifier = Modifier
                    .weight(.45f)
                    .padding(horizontal = horizontalTextPadding)
            ){
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            text = "Tiempo de preparacion: ",
                            fontStyle = MaterialTheme.typography.bodyMedium.fontStyle,
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize
                        )
                        BasicTextField(
                            value = addRecipeState.preparationTime.toString(),
                            onValueChange = { onEvent(AddRecipeScreenEvent.SetPreparationTime(it.toInt())) },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            ),
                            textStyle = MaterialTheme.typography.bodyMedium
                        )
                    }

                    Spacer(modifier = Modifier.size(16.dp))

                    BasicTextField(
                        value = addRecipeState.description,
                        onValueChange = { onEvent(AddRecipeScreenEvent.SetDescription(it)) },
                        textStyle = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        val imageCenterPositionModifier = Modifier
            .offset(x = -maxWidth * .05f, y = -maxHeight * .15f)


        val imageGraphicModifier = imageCenterPositionModifier
            .size(maxWidth * .6f)
            .clip(CircleShape)


        if(addRecipeState.image == null){

            Box(
                contentAlignment = Alignment.Center
            ){
                Image(
                    modifier = imageGraphicModifier,
                    painter = painterResource(R.drawable.empty_plate),
                    contentScale = ContentScale.Crop,
                    contentDescription = ""
                )
                IconButton(
                    modifier = imageCenterPositionModifier,
                    onClick = {
                        photoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    }
                ) {
                    Icon(
                        modifier = Modifier.size(64.dp),
                        imageVector =  Icons.Filled.Add,
                        contentDescription = "Add"
                    )
                }
            }



        }else{
            AsyncImage(
                modifier = imageGraphicModifier,
                model = addRecipeState.image,
                contentScale = ContentScale.Crop,
                contentDescription = "Image from gallery"
            )
        }

        IconButton(
            modifier = Modifier
                .offset(x = maxWidth * .35f, y = -maxHeight * 0.25f),
            onClick = {
                onEvent(AddRecipeScreenEvent.IsFavorite)
            }
        ) {
            Icon(
                modifier = Modifier.size(64.dp),
                imageVector = Icons.Filled.Star,
                contentDescription = "Favorite",
                tint = if(addRecipeState.isFavorite)  MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.onPrimary
            )
        }

    }
}

@Preview
@Composable
private fun AddRecipeWithImagePrev() {
    AppTheme {
        AddRecipeWithImage(
            addRecipeState = AddRecipeState(),
            onEvent = {}
        )
    }
}



@Preview
@Composable
private fun AsyncPrev() {
    var uri = Uri.parse("android.resource://com.example.recipeapp/empty_plate.png")
    AppTheme {
        Column(
            modifier = Modifier.background(Color.White)
        ) {
            Text(uri.path.toString())
            AddRecipeWithImage(
                addRecipeState = AddRecipeState(image = uri),
                onEvent = {}
            )
        }

    }
}