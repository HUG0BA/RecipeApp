package com.example.recipeapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipesDao {
    @Insert
    suspend fun insertRecipe(recipe: RecipeEntity)

    @Delete
    suspend fun deleteRecipe(recipe: RecipeEntity)

    @Update
    suspend fun updateRecipe(recipe: RecipeEntity)

    @Query("SELECT * FROM RecipeEntity WHERE id = :id")
    fun getRecipeById(id: Int): Flow<RecipeEntity>

    @Query("SELECT * FROM RecipeEntity ORDER BY title")
    fun getAllRecipesAlphabetical(): Flow<List<RecipeEntity>>

    @Query("SELECT * FROM RecipeEntity ORDER BY preparationTime ASC")
    fun getRecipesOrderedByPrepTimeDesc(): Flow<List<RecipeEntity>>

    @Query("SELECT * FROM RecipeEntity WHERE isFavorite")
    fun getFavoriteRecipes(): Flow<List<RecipeEntity>>
}