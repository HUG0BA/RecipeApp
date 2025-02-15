package com.example.recipeapp.viewmodels

import android.content.Context
import android.content.Intent
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.events.AddRecipeScreenEvent
import com.example.recipeapp.navigation.Navigator
import com.example.recipeapp.room.RecipeEntity
import com.example.recipeapp.room.RecipesDao
import com.example.recipeapp.state.AddRecipeState
import com.example.recipeapp.utility.SnackbarController
import com.example.recipeapp.utility.SnackbarEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddRecipeViewModel(
    private val recipesDao: RecipesDao,
    private val navigator: Navigator
): ViewModel() {
    private val _state = MutableStateFlow(AddRecipeState())
    val state = _state.asStateFlow()

    fun onEvent(event: AddRecipeScreenEvent){
        when(event){
            AddRecipeScreenEvent.AddRecipe -> {
                val title = _state.value.title
                val description = _state.value.description
                val preparationTime = _state.value.preparationTime
                val isFavorite = _state.value.isFavorite
                val image = _state.value.image

                if(title.isBlank()){
                    viewModelScope.launch {
                        SnackbarController.sendEvent(
                            event = SnackbarEvent(
                                message = "¡Agrega un titulo a tu receta!"
                            )
                        )
                    }
                    return
                }
                if(description.isBlank()){
                    viewModelScope.launch {
                        SnackbarController.sendEvent(
                            event = SnackbarEvent(
                                message = "¡Agrega una descripcion a tu receta!"
                            )
                        )
                    }
                    return
                }

                if(preparationTime <= 0){
                    viewModelScope.launch {
                        SnackbarController.sendEvent(
                            event = SnackbarEvent(
                                message = "¡Es imposible que cocines tan rapido! Tiempo de preparacion mayor a 0 minutos"
                            )
                        )
                    }
                    return
                }


                val recipe = RecipeEntity(
                    userId = 0,
                    title = title,
                    description =  description,
                    preparationTime =  preparationTime,
                    isFavorite =  isFavorite,
                    image = image?.toString()
                )

                viewModelScope.launch {
                    recipesDao.insertRecipe(recipe)
                    _state.update {
                        AddRecipeState()
                    }
                    navigator.navigateUp()
                }
            }
            is AddRecipeScreenEvent.SetDescription -> {
                _state.update {
                    it.copy(
                        description = event.description
                    )
                }
            }
            is AddRecipeScreenEvent.IsFavorite -> {
                _state.update {
                    it.copy(
                        isFavorite = !_state.value.isFavorite
                    )
                }
            }
            is AddRecipeScreenEvent.SetImage -> {
                _state.update {
                    it.copy(
                        image = event.image
                    )
                }
            }
            is AddRecipeScreenEvent.SetPreparationTime -> {
                _state.update {
                    it.copy(
                        preparationTime = event.preparationTime
                    )
                }
            }
            is AddRecipeScreenEvent.SetTitle -> {
                _state.update {
                    it.copy(title = event.title)
                }
            }
        }
    }

}