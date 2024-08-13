package com.example.librarymanagementsystem.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val icon: ImageVector, val title: String){
    object Home: BottomNavItem("home", Icons.Default.Home, "Home")
    object CreateBook: BottomNavItem("createBook", Icons.Default.Create, "Create Book")
    object Login: BottomNavItem("login", Icons.Default.AccountBox, "Login")
    //object DeleteBook: BottomNavItem("deleteBook", Icons.Default.Delete, "Delete Book")

    companion object{
        val allItems = listOf(Home, CreateBook)
    }
}

