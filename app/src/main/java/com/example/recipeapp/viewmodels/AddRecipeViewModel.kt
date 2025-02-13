package com.example.recipeapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.events.AddRecipeScreenEvent
import com.example.recipeapp.room.RecipeEntity
import com.example.recipeapp.room.RecipesDao
import com.example.recipeapp.state.AddRecipeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AddRecipeViewModel(
    val recipesDao: RecipesDao
): ViewModel() {
    val _state = MutableStateFlow(AddRecipeState())

    fun onEvent(event: AddRecipeScreenEvent){
        when(event){
            AddRecipeScreenEvent.AddRecipe -> {
                val title = _state.value.title
                val description = _state.value.description
                val preparationTime = _state.value.preparationTime
                val isFavorite = _state.value.isFavorite
                val image = _state.value.image

                if(title.isBlank() || description.isBlank()){
                    return
                }
                if(preparationTime <= 0){
                    return
                }

                val recipe = RecipeEntity(
                    userId = 0,
                    title = title,
                    description =  description,
                    preparationTime =  preparationTime,
                    isFavorite =  isFavorite,
                    image = image
                )

                viewModelScope.launch {
                    recipesDao.insertRecipe(recipe)
                }
            }
            is AddRecipeScreenEvent.SetDescription -> {
                _state.value.description = event.description
            }
            is AddRecipeScreenEvent.SetFavorite -> {
                _state.value.isFavorite = !_state.value.isFavorite
            }
            is AddRecipeScreenEvent.SetImage -> {
                _state.value.image = event.image
            }
            is AddRecipeScreenEvent.SetPreparationTime -> {
                _state.value.preparationTime = event.preparationTime
            }
            is AddRecipeScreenEvent.SetTitle -> {
                _state.value.title = event.title
            }
        }
    }

}