package com.example.library_management_android_app.presentation.UI.Authentication.signup

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
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.library_management_android_app.data_models.SignUp.SignUpRequest
import com.example.library_management_android_app.presentation.UI.bottombar.UiScreen.HomeScreen.MyTextField
import com.example.library_management_android_app.presentation.navigations.Screen

@Composable
fun SignupScreen(navController: NavHostController) {
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
            SignUpForm(navController)
        }
    }
}

@Composable
fun Header() {
    Box(
        modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Library Management", color = Color.White, fontSize = 30.sp
        )
    }
}


@Composable
fun SignUpForm(navController: NavHostController) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmpassword by remember { mutableStateOf("") }

    var isLoading by remember { mutableStateOf(false) }

    val context = LocalContext.current

    val signUpViewModel: SignUpViewModel = viewModel()
    val signupresponse by signUpViewModel.signupresponse.collectAsState()
    val issuccessfull by signUpViewModel.isSignUpSuccessful.collectAsState()

    Column(
        modifier = Modifier
            .padding(20.dp)
            .padding(top = 70.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        MyTextField(
            bookid = email,
            onValueChange = { email = it },
            placeholder = "Enter email",
            ImageVector = Icons.Default.Email
        )
        Spacer(modifier = Modifier.height(30.dp))

        MyTextField(
            bookid = password,
            onValueChange = { password = it },
            placeholder = "Enter your password",
            ImageVector = Icons.Default.Lock
        )
        Spacer(modifier = Modifier.height(30.dp))
        MyTextField(
            bookid = confirmpassword,
            onValueChange = { confirmpassword = it },
            placeholder = "Confirm your password",
            ImageVector = Icons.Default.Lock
        )
        Spacer(modifier = Modifier.height(30.dp))
        SignUpButton(
            isLoading = isLoading,
            onClick = {
                if (email.isNotEmpty() && password.isNotEmpty() && confirmpassword.isNotEmpty()) {
                    if (password == confirmpassword) {
                        isLoading = true
                        signUpViewModel.sign(SignUpRequest(email, password))
                    } else {
                        Toast.makeText(context, "Passwords don't match", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "Enter all fields", Toast.LENGTH_SHORT).show()
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = {
            navController.navigate(Screen.Signup.route) {
                popUpTo(navController.graph.startDestinationId) {
                    inclusive = true
                }
            }
        }) {
            Text("Already have an account! Login ", color = Color.White)
        }

        LaunchedEffect(signupresponse, issuccessfull) {
            Log.e("dmoe",signupresponse.toString() + issuccessfull)

            if (signupresponse != null || issuccessfull != "false") {
                isLoading = false
                when {
                    issuccessfull == "true" && signupresponse?.isSuccessful == true -> {
                        navController.navigate(Screen.MainApp.route)
                    }

                    issuccessfull != "false" -> {
                        Toast.makeText(context, issuccessfull, Toast.LENGTH_SHORT).show()
                        Log.e("Error failed", issuccessfull)
                    }

                    signupresponse != null -> {
                        val errorMessage = signupresponse?.errorBody()?.string() ?: "Signup failed"
                        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                        Log.e("Error failed", errorMessage)
                    }
                }
                // Reset ViewModel state after handling the response
                signUpViewModel.resetState()
            }
        }

    }
}

@Composable
fun SignUpButton(
    isLoading: Boolean,
    onClick: () -> Unit
) {
    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xff972CB0), disabledContainerColor = Color.Gray
        ),
        modifier = Modifier
            .padding(top = 20.dp, start = 20.dp, end = 20.dp)
            .fillMaxWidth(),
        onClick = onClick,
        enabled = !isLoading
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp),
                color = Color.White
            )
        } else {
            Text("SignUp")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview() {
    SignupScreen(navController = rememberNavController())
}