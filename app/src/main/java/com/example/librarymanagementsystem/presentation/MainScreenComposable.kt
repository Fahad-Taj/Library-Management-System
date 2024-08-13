package com.example.librarymanagementsystem.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.librarymanagementsystem.R
import com.example.librarymanagementsystem.ui.theme.bottomBarSelected
import com.example.librarymanagementsystem.ui.theme.topBarColor

// This will be a scaffold which will contain all the skeleton of the app
@Composable
fun MainScreenComposable(){
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopBar()
        },
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) {

        Column(
            modifier = Modifier.padding(it)
        ) {
            NavGraph(navController = navController)
        }
    }

}

@Composable
fun TopBar(){
    Row(
        modifier = Modifier
            .height(110.dp)
            .background(topBarColor)
            .statusBarsPadding()
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Library Management System",
            color = Color.White,
            fontFamily = FontFamily(Font(R.font.pacifico)),
            fontSize = 22.sp
        )
    }
}

@Composable
fun BottomBar(navController: NavHostController){

    BottomNavigation(
        modifier = Modifier
            .height(80.dp),
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        BottomNavItem.allItems.forEach { item ->
            val selected = currentRoute == item.route
            BottomNavigationItem(
                selected = selected,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                    item.icon,
                    contentDescription = null,
                    tint = if(selected) Color.White else bottomBarSelected
                )
                       },
                label = { Text(
                    item.title,
                    color = if(selected) Color.White else bottomBarSelected,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                ) },
                modifier = Modifier.background(
                    if(selected) bottomBarSelected else Color.White
                )
            )
        }
    }
}
