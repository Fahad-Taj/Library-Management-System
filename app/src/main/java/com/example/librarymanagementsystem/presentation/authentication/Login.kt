package com.example.librarymanagementsystem.presentation.authentication

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.librarymanagementsystem.presentation.BottomNavItem
import com.example.librarymanagementsystem.ui.theme.topBarColor
import kotlin.math.log

@Composable
fun Login(navController: NavHostController){
    val loginViewModel: LoginViewModel = viewModel()
    val response by loginViewModel.response.collectAsState()

    // Main Column
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Email field

        androidx.compose.material3.TextField(
            modifier = Modifier
                .width(260.dp)
                .border(1.dp, topBarColor, RoundedCornerShape(12.dp)),
            value = loginViewModel.email.value,
            onValueChange = {
                loginViewModel.email.value = it
            },
            textStyle = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            ),
            label = { androidx.compose.material3.Text(text = "Email") },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
        )
        Spacer(modifier = Modifier.height(20.dp))

        // Password field
        androidx.compose.material3.TextField(
            modifier = Modifier
                .width(260.dp)
                .border(1.dp, topBarColor, RoundedCornerShape(12.dp)),
            value = loginViewModel.password.value,
            onValueChange = {
                loginViewModel.password.value = it
            },
            textStyle = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            ),
            label = { androidx.compose.material3.Text(text = "Password") },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
        )
        Spacer(modifier = Modifier.height(20.dp))

        // Login button
        Button(
            modifier = Modifier.height(50.dp),
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = topBarColor
            ),
            onClick = { loginViewModel.login() }) {
            androidx.compose.material3.Text(
                text = "Login",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        Spacer(modifier = Modifier.height(200.dp))


        // Signup
        Button(
            modifier = Modifier.height(50.dp),
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = topBarColor
            ),
            onClick = { navController.navigate("signup") }) {
            androidx.compose.material3.Text(
                text = "Signup",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        LaunchedEffect(response) {
            response?.let {
                if (it.isSuccessful) {
                    navController.navigate("main") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            }
        }
    }

}