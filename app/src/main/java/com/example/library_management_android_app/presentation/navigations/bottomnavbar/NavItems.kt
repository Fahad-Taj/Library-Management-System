package com.example.library_management_android_app.presentation.navigations.bottomnavbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Home

import androidx.compose.ui.graphics.vector.ImageVector

data class NavItems(
    val label: String, val icon: ImageVector, val route: String
)

val listOfNavItems = listOf(
    NavItems(
        label = "Home Screen", icon = Icons.Outlined.Home, route = BottomScreens.Home.name
    ), NavItems(
        label = "Add Book", icon = Icons.Outlined.Add, route = BottomScreens.Create.name
    ), NavItems(
        label = "Modify Book", icon = Icons.Outlined.Edit, route = BottomScreens.Update.name
    )
)
