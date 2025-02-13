package com.example.recipeapp.viewmodels

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.events.RecipeDetailsEvent
import com.example.recipeapp.room.RecipeEntity
import com.example.recipeapp.room.RecipesDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RecipeDetailsViewModel(
    private val recipesDao: RecipesDao,
    private val recipeId: Int
): ViewModel() {
    private val _recipe = recipesDao.getRecipeById(recipeId)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), RecipeEntity(0,"","",0, false, ""))
    val recipe = _recipe

    fun onEvent(event: RecipeDetailsEvent){
        when(event){
            RecipeDetailsEvent.IsFavorite -> viewModelScope.launch{
                _recipe.value.isFavorite != _recipe.value.isFavorite
                recipesDao.updateRecipe(_recipe.value)
            }
        }
    }
}