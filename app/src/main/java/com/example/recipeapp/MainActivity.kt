package com.example.recipeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.recipeapp.navigation.DefaultNavigator
import com.example.recipeapp.navigation.Destination
import com.example.recipeapp.navigation.NavigationAction
import com.example.recipeapp.ui.theme.AppTheme
import com.example.recipeapp.utility.ObserveAsEvents

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val navigator = DefaultNavigator(startDestination = Destination.MainScreen)

        setContent {
            AppTheme {
                val navController = rememberNavController()

                ObserveAsEvents(flow = navigator.navigateActions) { action ->
                    when (action) {
                        is NavigationAction.Navigate -> navController.navigate(
                            action.destination
                        ){
                            action.navOptions(this)
                        }
                        NavigationAction.NavigateUp -> navController.navigateUp()
                    }
                }

                NavHost(
                    navController = navController,
                    startDestination = navigator.startDestination
                ) {
                    composable<Destination.MainScreen> {

                    }

                    composable<Destination.RecipeDetailsScreen> {
                        val args = it.toRoute<Destination.RecipeDetailsScreen>()
                    }

                    composable<Destination.AddRecipeScreen> {

                    }
                }
            }
        }
    }
}




@Preview
@Composable
private fun MainPrev() {

}