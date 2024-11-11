package com.example.library_management_android_app.presentation.UI.bottombar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.library_management_android_app.presentation.navigations.Screen
import com.example.library_management_android_app.presentation.navigations.bottomnavbar.NavGraph

import com.example.library_management_android_app.presentation.navigations.bottomnavbar.listOfNavItems

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(navController: NavHostController,modifier: Modifier = Modifier) {
    val bottomNavController = rememberNavController()

    Scaffold(modifier = Modifier.navigationBarsPadding(),
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Library Management",
                            color = Color.White
                        )
                    }

                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black, titleContentColor = Color.White
                ),
                actions = {
                    IconButton(onClick = {
                        navController.navigate(Screen.Login.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                inclusive = true
                            }
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Logout, // Use the default Logout icon
                            contentDescription = "Logout",
                            tint = Color.White
                        )
                    }
                }
                )

        },
        containerColor = Color.Black,
        bottomBar = { BottomBar(navController = bottomNavController) })
    {
        paddingValues ->
        Column(modifier.padding(paddingValues) ) {
            NavGraph(navController = bottomNavController)
        }


    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    BottomNavigation(backgroundColor = Color.Black, contentColor = Color.White) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val BottomNavSelectedColor = Color(0xFF6C63FF) // Purple color for selected items
        val BottomNavUnselectedColor = Color(0xFFB0B0B0)

        listOfNavItems.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = "",
                        tint = if (currentRoute == screen.route) BottomNavSelectedColor else BottomNavUnselectedColor
                        , modifier = Modifier.clipToBounds()
                    )
                },
                label = {
                    Text(
                        screen.label,
                        color = if (currentRoute == screen.route) BottomNavSelectedColor else BottomNavUnselectedColor
                    )
                },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo = navController.graph.startDestinationId
                        launchSingleTop = true
                    }
                },
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    AppNavigation(navController = rememberNavController())
}