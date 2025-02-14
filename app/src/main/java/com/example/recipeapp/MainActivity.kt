package com.example.recipeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import androidx.room.Room
import com.example.recipeapp.navigation.DefaultNavigator
import com.example.recipeapp.navigation.Destination
import com.example.recipeapp.navigation.NavigationAction
import com.example.recipeapp.room.AppDatabase
import com.example.recipeapp.state.AddRecipeState
import com.example.recipeapp.ui.screens.AddRecipeScreen
import com.example.recipeapp.ui.screens.MainScreen
import com.example.recipeapp.ui.screens.RecipeDetailsScreen
import com.example.recipeapp.ui.theme.AppTheme
import com.example.recipeapp.utility.ObserveAsEvents
import com.example.recipeapp.viewmodels.AddRecipeViewModel
import com.example.recipeapp.viewmodels.MainViewModel
import com.example.recipeapp.viewmodels.RecipeDetailsViewModel

class MainActivity : ComponentActivity() {
    private val db by lazy{
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "recipesv1.db"
        ).build()
    }

    val navigator = DefaultNavigator(startDestination = Destination.MainScreen)

     private val mainViewmodel by viewModels<MainViewModel> (
         factoryProducer = {
             object: ViewModelProvider.Factory {
                 override fun <T : ViewModel> create(modelClass: Class<T>): T {
                     return MainViewModel(
                         recipesDao = db.recipesDao,
                         navigator = navigator
                     ) as T
                 }
             }
         }
     )

    private val recipeDetailsViewModel by viewModels<RecipeDetailsViewModel> (
        factoryProducer = {
            object: ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return RecipeDetailsViewModel(
                        recipesDao = db.recipesDao
                    ) as T
                }
            }
        }
    )


    private val addRecipeViewModel by viewModels<AddRecipeViewModel> (
        factoryProducer = {
            object: ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return AddRecipeViewModel(
                        recipesDao = db.recipesDao
                    ) as T
                }
            }
        }
    )

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
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
                        val state by mainViewmodel.state.collectAsState()
                        MainScreen(mainScreenState = state, onEvent = mainViewmodel::onEvent)
                    }

                    composable<Destination.RecipeDetailsScreen> {
                        val args = it.toRoute<Destination.RecipeDetailsScreen>()
                        recipeDetailsViewModel.setRecipeId(args.id)
                        val state by recipeDetailsViewModel.recipe.collectAsState()
                        RecipeDetailsScreen(
                            recipe = state,
                            onEvent = recipeDetailsViewModel::onEvent
                        )
                    }

                    composable<Destination.AddRecipeScreen> {
                        val state by addRecipeViewModel.state.collectAsState()
                        AddRecipeScreen(addRecipeState = state, onEvent = addRecipeViewModel::onEvent)
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