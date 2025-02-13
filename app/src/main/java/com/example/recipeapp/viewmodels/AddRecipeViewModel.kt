package com.example.recipeapp.viewmodels

import androidx.lifecycle.ViewModel
import com.example.recipeapp.events.AddRecipeScreenEvent
import com.example.recipeapp.room.RecipesDao

class AddRecipeViewModel(
    recipesDao: RecipesDao
): ViewModel() {

    fun onEvent(event: AddRecipeScreenEvent){
        when(event){
            AddRecipeScreenEvent.AddRecipe -> TODO()
            is AddRecipeScreenEvent.SetDescription -> TODO()
            is AddRecipeScreenEvent.SetFavorite -> TODO()
            is AddRecipeScreenEvent.SetImage -> TODO()
            is AddRecipeScreenEvent.SetPreparationTime -> TODO()
            is AddRecipeScreenEvent.SetTitle -> TODO()
        }
    }

}