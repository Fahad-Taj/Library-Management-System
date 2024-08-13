package com.example.librarymanagementsystem.presentation.authentication

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.librarymanagementsystem.presentation.MainScreenComposable

@Composable
fun AuthenticationGraph(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login"){ Login(navController) }
        composable("signup"){ SignUp(navController) }
        composable("main"){ MainScreenComposable() }
    }
}