package com.example.recipeapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.recipeapp.ui.screens.AddRecipeScreen
import com.example.recipeapp.ui.screens.MainScreen
import com.example.recipeapp.ui.screens.RecipeDetailsScreen
import com.example.recipeapp.ui.theme.AppTheme


/*
@Composable
fun MainNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MainScreenDestination
    ){
        composable<MainScreenDestination> {
            MainScreen(
                onCardClick = { navController.navigate(RecipeDetailsDestination) },
                onFloatingButtonClick = { navController.navigate(AddRecipeNavigation) }
            )
        }
        composable<RecipeDetailsDestination> {
            RecipeDetailsScreen()
        }
        composable<AddRecipeNavigation> {
            AddRecipeScreen()
        }
    }
}

@Preview
@Composable
private fun MainNavigationPrev() {
    AppTheme {
        MainNavigation()
    }
}*/