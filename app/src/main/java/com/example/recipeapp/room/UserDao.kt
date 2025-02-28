package com.example.recipeapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM UserEntity WHERE email = :email AND password = :password")
    suspend fun getLoginUser(email: String, password: String): UserEntity?

    @Query("SELECT * FROM UserEntity WHERE id = :userId")
    fun getUserById(userId: Int): Flow<UserEntity>
}