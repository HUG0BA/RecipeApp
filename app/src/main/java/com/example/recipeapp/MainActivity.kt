package com.example.recipeapp

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import androidx.room.Room
import com.example.recipeapp.datasore.LoginPreferences
import com.example.recipeapp.datasore.LoginPreferencesRepository
import com.example.recipeapp.navigation.DefaultNavigator
import com.example.recipeapp.navigation.Destination
import com.example.recipeapp.navigation.NavigationAction
import com.example.recipeapp.room.AppDatabase
import com.example.recipeapp.room.UserEntity
import com.example.recipeapp.state.AddRecipeState
import com.example.recipeapp.ui.screens.AddRecipeScreen
import com.example.recipeapp.ui.screens.LoginScreen
import com.example.recipeapp.ui.screens.MainScreen
import com.example.recipeapp.ui.screens.RecipeDetailsScreen
import com.example.recipeapp.ui.screens.SignUpScreen
import com.example.recipeapp.ui.screens.SplashScreen
import com.example.recipeapp.ui.theme.AppTheme
import com.example.recipeapp.utility.ObserveAsEvents
import com.example.recipeapp.utility.SnackbarController
import com.example.recipeapp.viewmodels.AddRecipeViewModel
import com.example.recipeapp.viewmodels.LoginViewModel
import com.example.recipeapp.viewmodels.MainViewModel
import com.example.recipeapp.viewmodels.RecipeDetailsViewModel
import com.example.recipeapp.viewmodels.SignUpViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val LOGIN_PREFERENCES_NAME = "login_preferences"

private val Context.dataStore by preferencesDataStore(
    name = LOGIN_PREFERENCES_NAME
)
class MainActivity : ComponentActivity() {


    private val db by lazy{
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "recipesv1.db"
        ).build()
    }


    val navigator = DefaultNavigator(startDestination = Destination.SplashScreen)

    private val loginViewModel by viewModels<LoginViewModel> (
        factoryProducer = {
            object: ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return LoginViewModel(
                        loginPreferencesRepository = LoginPreferencesRepository(dataStore = dataStore),
                        userDao = db.userDao,
                        navigator = navigator
                    ) as T
                }
            }
        }
    )

    private val signUpViewModel by viewModels<SignUpViewModel> (
        factoryProducer = {
            object: ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return SignUpViewModel(
                        userDao = db.userDao,
                        navigator = navigator
                    ) as T
                }
            }
        }
    )

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
                        recipesDao = db.recipesDao,
                        navigator = navigator
                    ) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val loginPreferencesRepository = LoginPreferencesRepository(dataStore = dataStore)


        setContent {
            AppTheme {
                val scope = rememberCoroutineScope()


                LaunchedEffect(Unit) {
                    scope.launch {
                        db.userDao.insertUser(UserEntity("Koalit", "info@koalit.dev", "koalit123"))
                        val preferences = loginPreferencesRepository.fetchInitialPreferences()
                        delay(750)
                        if(preferences.isLogged){
                            navigator.navigate(Destination.MainScreen)
                        }else {
                            navigator.navigate(Destination.LoginScreen)
                        }
                    }
                }



                val navController = rememberNavController()

                val snackbarHostState = remember { SnackbarHostState()}




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



                ObserveAsEvents(
                    flow = SnackbarController.events,
                    key1 = snackbarHostState
                ) { event ->
                    scope.launch {
                        snackbarHostState.currentSnackbarData?.dismiss()
                        snackbarHostState.showSnackbar(
                            message = event.message,
                            actionLabel = event.action?.name,
                            duration = SnackbarDuration.Short
                        )
                    }
                }
                Scaffold(
                    snackbarHost = {
                        SnackbarHost(hostState = snackbarHostState)
                    }
                ) { innerPadding ->
                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        startDestination = navigator.startDestination
                    ) {
                        composable<Destination.SplashScreen> {
                            SplashScreen()
                        }

                        composable<Destination.LoginScreen> {
                            val state by loginViewModel.state.collectAsState()
                            LoginScreen(loginState = state, onEvent = loginViewModel::onEvent)
                        }

                        composable<Destination.SignUpScreen> {
                            val state by signUpViewModel.state.collectAsState()
                            SignUpScreen(signUpState = state, onEvent = signUpViewModel::onEvent)
                        }

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
}




@Preview
@Composable
private fun MainPrev() {

}