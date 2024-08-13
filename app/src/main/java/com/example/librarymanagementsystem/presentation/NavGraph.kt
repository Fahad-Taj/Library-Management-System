package com.example.librarymanagementsystem.presentation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.librarymanagementsystem.presentation.authentication.Login
import com.example.librarymanagementsystem.presentation.inner_composables.CreateBookComposable
import com.example.librarymanagementsystem.presentation.inner_composables.HomeComposable
import com.example.librarymanagementsystem.presentation.inner_composables.HomeViewModel



@Composable
fun NavGraph(navController: NavHostController){
    val homeScreenViewmodel: HomeViewModel = viewModel()
    NavHost(navController = navController, startDestination = BottomNavItem.Home.route) {
        composable(BottomNavItem.Home.route){ HomeComposable(homeScreenViewmodel) }
        composable(BottomNavItem.CreateBook.route){ CreateBookComposable() }
        //composable(BottomNavItem.DeleteBook.route){ DeleteBookComposable() }
    }
}