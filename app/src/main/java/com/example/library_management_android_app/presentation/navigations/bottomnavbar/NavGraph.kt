package com.example.library_management_android_app.presentation.navigations.bottomnavbar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.library_management_android_app.presentation.UI.bottombar.UiScreen.Create.CreateScreen
import com.example.library_management_android_app.presentation.UI.bottombar.UiScreen.HomeScreen.MyScreen
import com.example.library_management_system.presentation.UI.bottombar.UiScreen.Update.UpdateScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomScreens.Home.name,
    ) {
        composable(route = BottomScreens.Home.name) {
            MyScreen()
        }
        composable(route = BottomScreens.Update.name) {
            UpdateScreen(navController)
        }
        composable(route = BottomScreens.Create.name) {
            CreateScreen(navController)
        }

    }
}