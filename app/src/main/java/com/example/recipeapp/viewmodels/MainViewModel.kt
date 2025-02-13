package com.example.recipeapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.events.MainScreenEvent
import com.example.recipeapp.models.Filters
import com.example.recipeapp.room.RecipesDao
import com.example.recipeapp.state.MainScreenState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModel(
    recipesDao: RecipesDao,
    val onAddRecipe: () -> Unit,
    val onSeeRecipeDetails: () -> Unit
) : ViewModel(){
    private val _filter = MutableStateFlow(Filters.NONE)

    private val _recipes = _filter
        .flatMapLatest {  filer ->
            when(filer){
                Filters.NONE -> recipesDao.getAllRecipes()
                Filters.FAVORITE -> recipesDao.getFavoriteRecipes()
                Filters.PREPARATION_TIME_DESCENDING -> recipesDao.getRecipesOrderedByPrepTimeDesc()
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(MainScreenState())

    val state = combine(_state, _filter, _recipes){ state, filter, recipes ->
        state.copy(
            recipes = recipes,
            filter = filter
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), MainScreenState())

    fun onEvent(event: MainScreenEvent){
        when(event) {
            MainScreenEvent.AddRecipe -> onAddRecipe
            is MainScreenEvent.SeeRecipeDetails -> onSeeRecipeDetails
            is MainScreenEvent.SelectFilter -> {
                _filter.value = event.filter
            }
        }
    }
}