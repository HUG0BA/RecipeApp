package com.example.recipeapp.viewmodels

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.tools.build.jetifier.core.utils.Log
import com.example.recipeapp.events.RecipeDetailsEvent
import com.example.recipeapp.room.RecipeEntity
import com.example.recipeapp.room.RecipesDao
import com.example.recipeapp.state.RecipeDetailsState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class RecipeDetailsViewModel(
    private val recipesDao: RecipesDao
): ViewModel() {
    private val recipeId = MutableStateFlow(-1)

    private val _recipe = recipeId
        .filter {
            it >= 0
        }
        .flatMapLatest { id ->
                recipesDao.getRecipeById(id)
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), RecipeEntity(0, "", "", 0, false))

    val recipe = _recipe

    fun onEvent(event: RecipeDetailsEvent){
        when(event){
            RecipeDetailsEvent.IsFavorite -> viewModelScope.launch{

                recipesDao.updateRecipe(
                    _recipe.value.copy(isFavorite = !_recipe.value.isFavorite)
                )
            }
        }
    }

    fun setRecipeId(id: Int){
        recipeId.update {
            id
        }
    }
}