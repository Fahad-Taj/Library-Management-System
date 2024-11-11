package com.example.library_management_android_app.presentation.UI.Authentication.login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.library_management_android_app.data_models.Login.LoginRequest
import com.example.library_management_android_app.presentation.UI.Authentication.signup.Header
import com.example.library_management_android_app.presentation.UI.bottombar.UiScreen.HomeScreen.MyTextField
import com.example.library_management_android_app.presentation.navigations.Screen


@Composable
fun LoginScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 45.dp)
        ) {
            Header()
            LoginInForm(navController = navController)
        }
    }
}

@Composable
fun LoginInForm(navController: NavHostController) {
    val loginViewModel: LoginViewModel = viewModel()
    val loginresponse by loginViewModel.loginresponse.collectAsState()
    val isSuccessful by loginViewModel.isLoginSuccessful.collectAsState()
    val context = LocalContext.current

    var isLoading by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(20.dp)
            .padding(top = 90.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MyTextField(
            bookid = email,
            onValueChange = { email = it },
            placeholder = "Enter email",
            ImageVector = Icons.Default.Email
        )
        Spacer(modifier = Modifier.height(40.dp))

        MyTextField(
            bookid = password,
            onValueChange = { password = it },
            placeholder = "Enter password",
            ImageVector = Icons.Default.Lock
        )
        Spacer(modifier = Modifier.height(40.dp))

        LoginButton(isLoading = isLoading) {
            if (email.isNotEmpty() && password.isNotEmpty()) {
                isLoading = true
                loginViewModel.login(LoginRequest(email, password))
            } else {
                Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = { navController.navigate(Screen.Signup.route) }) {
            Text("Don't have an account? Sign up", color = Color.White)
        }

        // Launched Effect
        LaunchedEffect(loginresponse, isSuccessful) {
            isLoading = false
            Log.e(
                "dmoe", "LoginResponse: ${loginresponse?.toString()}, IsSuccessful: $isSuccessful"
            )
            if (loginresponse != null || isSuccessful.toString() != "false") {
                when {
                    isSuccessful == "true" && loginresponse?.isSuccessful == true -> {
                        navController.navigate(Screen.MainApp.route)
                    }

                    isSuccessful != "false" -> {
                        Toast.makeText(context, isSuccessful, Toast.LENGTH_SHORT).show()
                        Log.e("Error failed", isSuccessful)
                    }

                    loginresponse != null -> {
                        val errorMessage = loginresponse!!.errorBody()?.string() ?: "Login failed"
                        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                        Log.e("Error failed", errorMessage)
                    }
                }
                loginViewModel.resetState()
            }
        }
    }
}

@Composable
fun LoginButton(isLoading: Boolean, onClick: () -> Unit) {
    Button(
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xff972CB0)),
        modifier = Modifier
            .padding(top = 20.dp, start = 20.dp, end = 20.dp)
            .fillMaxWidth(),
        onClick = onClick,
        enabled = !isLoading
    ) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.size(25.dp), color = Color.White)
        } else {
            Text("Login")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview() {
    LoginScreen(navController = rememberNavController())
}
