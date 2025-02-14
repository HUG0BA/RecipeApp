package com.example.recipeapp.room

import android.net.Uri
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
    val image: String? = null,
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

val recipeDummies = listOf(
    RecipeEntity(1, "Spaghetti Bolognese", "Classic Italian pasta with meat sauce", 30, true),
    RecipeEntity(2, "Chicken Curry", "Spicy and creamy chicken curry", 45, false, null),
    RecipeEntity(3, "Beef Stroganoff", "Tender beef strips in a creamy mushroom sauce", 40, true),
    RecipeEntity(4, "Vegetable Stir Fry", "Mixed vegetables in a savory soy sauce", 20, false),
    RecipeEntity(5, "Margherita Pizza", "Classic pizza with tomato, mozzarella, and basil", 25, true),
    RecipeEntity(6, "Tacos", "Soft tacos with beef, cheese, and lettuce", 15, false),
    RecipeEntity(7, "Lemon Chicken", "Crispy chicken in a tangy lemon sauce", 35, true),
    RecipeEntity(8, "Salmon Teriyaki", "Grilled salmon with teriyaki glaze", 30, false),
    RecipeEntity(9, "Chocolate Cake", "Rich chocolate cake with frosting", 50, true),     RecipeEntity(10, "Caesar Salad", "Crisp romaine lettuce with Caesar dressing", 15, false)
)

val recipeDummy = RecipeEntity(
    userId = 11,
    title = "Pancakes",
    description = "Fluffy pancakes with syrup",
    preparationTime = 20,
    isFavorite = false
)

val favRecipeDummy = RecipeEntity(
    userId = 11,
    title = "Pancakes",
    description = "Fluffy pancakes with syrup",
    preparationTime = 20,
    isFavorite = true
)
