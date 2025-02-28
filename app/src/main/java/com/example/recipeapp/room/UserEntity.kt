package com.example.recipeapp.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    val username: String,
    val email: String,
    val password: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
