package com.example.recipeapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun SplashScreen(modifier: Modifier = Modifier) {
    Surface(modifier = Modifier.fillMaxSize()){
        Box(
            contentAlignment = Alignment.Center
        ){
            Text("Recipe App")
        }

    }
}