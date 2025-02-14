package com.example.recipeapp.viewmodels

import android.content.Context
import android.content.Intent
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.events.AddRecipeScreenEvent
import com.example.recipeapp.room.RecipeEntity
import com.example.recipeapp.room.RecipesDao
import com.example.recipeapp.state.AddRecipeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddRecipeViewModel(
    val recipesDao: RecipesDao
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

                if(title.isBlank() || description.isBlank()){
                    return
                }
                if(preparationTime <= 0){
                    return
                }

                if(image != null){

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