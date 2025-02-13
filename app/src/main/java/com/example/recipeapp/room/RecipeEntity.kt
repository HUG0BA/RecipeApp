package com.example.recipeapp.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(

)
data class RecipeEntity(
    val userId: Int,
    val title: String,
    val description: String,
    val preparationTime: Int,
    val isFavorite: Boolean,
    val image: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)

/*

foreignKeys = [ForeignKey(
        entity = UserEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("userId")
    )]
 */
