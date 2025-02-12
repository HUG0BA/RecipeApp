package com.example.recipeapp.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [UserEntity::class, RecipeEntity::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract val recipesDao: RecipesDao
    abstract val userDao: UserDao
}