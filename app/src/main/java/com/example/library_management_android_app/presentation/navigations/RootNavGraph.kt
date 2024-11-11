package com.example.library_management_android_app.presentation.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.library_management_android_app.presentation.UI.bottombar.AppNavigation
import com.example.library_management_android_app.presentation.UI.Authentication.login.LoginScreen
import com.example.library_management_android_app.presentation.UI.Authentication.signup.SignupScreen

@Composable
fun RootNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) {
            LoginScreen(navController)
        }
        composable(Screen.Signup.route) {
            SignupScreen(navController)
        }
        composable(Screen.MainApp.route) {
            AppNavigation(navController)
        }
    }
}


sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Signup : Screen("signup")
    object MainApp : Screen("mainApp")
}