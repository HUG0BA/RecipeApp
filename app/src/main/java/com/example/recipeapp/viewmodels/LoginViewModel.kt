package com.example.recipeapp.viewmodels

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.datasore.LoginPreferences
import com.example.recipeapp.datasore.LoginPreferencesRepository
import com.example.recipeapp.events.LoginEvent
import com.example.recipeapp.navigation.Destination
import com.example.recipeapp.navigation.Navigator
import com.example.recipeapp.room.UserDao
import com.example.recipeapp.room.UserEntity
import com.example.recipeapp.state.LoginState
import com.example.recipeapp.utility.SnackbarController
import com.example.recipeapp.utility.SnackbarEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jetbrains.annotations.Async.Execute

class LoginViewModel(
    private val userDao: UserDao,
    private val loginPreferencesRepository: LoginPreferencesRepository,
    private val navigator: Navigator
): ViewModel() {

    private val _state = MutableStateFlow(LoginState())

    val state = _state.asStateFlow()

    fun onEvent(event: LoginEvent){
        when(event){
            is LoginEvent.SetEmail -> {
                _state.update {
                    it.copy(
                        email = event.email
                    )
                }
            }
            is LoginEvent.SetPassword -> {
                _state.update {
                    it.copy(
                        password = event.password
                    )
                }
            }
            is LoginEvent.Submit -> {
                val email = _state.value.email
                val password = _state.value.password

                if(email.isBlank() || password.isBlank()){
                    viewModelScope.launch {
                        SnackbarController.sendEvent(
                            event = SnackbarEvent(
                                message = "Por favor, llene todos los campos"
                            )
                        )
                    }
                    return
                }

                viewModelScope.launch {
                    var user: UserEntity? = try{ userDao.getLoginUser(email, password) }
                    catch (e: Exception){
                        Log.i("LOGINVIEWMODEL", e.toString())
                        null
                    }


                    if(user == null){
                        SnackbarController.sendEvent(
                            event = SnackbarEvent(
                                message = "Correo o constrasena incorrectos"
                            )
                        )
                    } else{
                        loginPreferencesRepository.updateIsLogged(user.id)
                        navigator.navigate(Destination.MainScreen)
                    }
                }

            }
            LoginEvent.SignUp -> viewModelScope.launch{
                navigator.navigate(Destination.SignUpScreen)
            }
        }
    }
}