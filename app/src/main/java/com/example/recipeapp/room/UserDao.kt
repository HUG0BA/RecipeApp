package com.example.recipeapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM UserEntity WHERE id = :userId")
    fun getUserById(userId: Int): Flow<UserEntity>
}