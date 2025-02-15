package com.example.recipeapp.viewmodels

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.datasore.LoginPreferences
import com.example.recipeapp.events.LoginEvent
import com.example.recipeapp.navigation.Destination
import com.example.recipeapp.navigation.Navigator
import com.example.recipeapp.room.UserDao
import com.example.recipeapp.state.LoginState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val userDao: UserDao,
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
                    return
                }

                val user = userDao.getLoginUser(email = email, password = password)

                if()
            }
            LoginEvent.SignUp -> viewModelScope.launch{
                navigator.navigate(Destination.SignUpScreen)
            }
        }
    }
}