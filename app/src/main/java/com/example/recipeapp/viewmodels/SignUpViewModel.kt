package com.example.recipeapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.events.SignUpEvent
import com.example.recipeapp.room.UserDao
import com.example.recipeapp.room.UserEntity
import com.example.recipeapp.state.SignUpState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val userDao: UserDao
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
                    return
                }

                if(password != confirmPassword){
                    return
                }

                val newUser = UserEntity(
                    username = user,
                    email = email,
                    password = password
                )

                viewModelScope.launch {
                    userDao.insertUser(newUser)
                }
            }
        }
    }
}