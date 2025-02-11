package com.example.recipeapp.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class FilterChipModel(val name: String) {
    var isSelected by mutableStateOf(false)

    fun onClick(){
        isSelected = !isSelected
    }
}