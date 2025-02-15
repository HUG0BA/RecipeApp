package com.example.recipeapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.events.SignUpEvent
import com.example.recipeapp.navigation.Destination
import com.example.recipeapp.navigation.Navigator
import com.example.recipeapp.room.UserDao
import com.example.recipeapp.room.UserEntity
import com.example.recipeapp.state.SignUpState
import com.example.recipeapp.utility.SnackbarController
import com.example.recipeapp.utility.SnackbarEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val userDao: UserDao,
    private val navigator: Navigator
): ViewModel() {
    private val _state = MutableStateFlow(SignUpState())
    val state = _state.asStateFlow()

    fun onEvent(event: SignUpEvent){
        when(event){
            is SignUpEvent.SetConfirmPassword -> {
                _state.update {
                    it.copy(confirmPassword = event.confirmPassword)
                }
            }
            is SignUpEvent.SetEmail -> {
                _state.update {
                    it.copy(email = event.email)
                }
            }
            is SignUpEvent.SetPassword -> {
                _state.update {
                    it.copy(password = event.password)
                }
            }
            is SignUpEvent.SetUser -> {
                _state.update {
                    it.copy(user = event.user)
                }
            }
            SignUpEvent.Submit -> {
                val user = _state.value.user
                val email = _state.value.email
                val password = _state.value.password
                val confirmPassword = _state.value.confirmPassword

                if(user.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank()){
                    viewModelScope.launch {
                        SnackbarController.sendEvent(
                            event = SnackbarEvent(
                                message = "Por favor, complete todos los campos"
                            )
                        )
                    }
                    return
                }

                if(password != confirmPassword){
                    viewModelScope.launch {
                        SnackbarController.sendEvent(
                            event = SnackbarEvent(
                                message = "Las constrasenas no son iguales"
                            )
                        )
                    }
                    return
                }

                val newUser = UserEntity(
                    username = user,
                    email = email,
                    password = password
                )

                viewModelScope.launch {
                    userDao.insertUser(newUser)
                    _state.update {
                        SignUpState()
                    }
                    navigator.navigate(Destination.LoginScreen)
                }
            }
        }
    }
}