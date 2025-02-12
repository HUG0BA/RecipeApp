package com.example.recipeapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipesDao {
    @Insert
    suspend fun insertRecipe(recipe: RecipeEntity)

    @Delete
    suspend fun deleteRecipe(recipe: RecipeEntity)

    @Query("SELECT * FROM RecipeEntity ORDER BY preparationTime DESC")
    fun getRecipesOrderedByPrepTimeDesc(): Flow<List<RecipeEntity>>

    @Query("SELECT * FROM RecipeEntity WHERE isFavorite")
    fun getFavoriteRecipes(): Flow<List<RecipeEntity>>
}