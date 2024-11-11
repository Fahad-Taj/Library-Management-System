package com.example.library_management_android_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.library_management_android_app.presentation.navigations.RootNavigation

import com.example.library_management_system.ui.theme.Library_Management_SystemTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Library_Management_SystemTheme {
                var showSplashScreen by remember { mutableStateOf(true) }

                if (showSplashScreen) {
                    SplashScreen {
                        showSplashScreen = false
                    }
                } else {
                    // Your main app content
                    val navController = rememberNavController()
                    RootNavigation(navController = navController)
                }
            }
        }
    }
}

@Composable
fun SplashScreen(onSplashScreenFinished: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash))
        val logoAnimationState = animateLottieCompositionAsState(composition = composition)
        LottieAnimation(
            composition = composition,
            progress = logoAnimationState.progress
        )

        LaunchedEffect(key1 = logoAnimationState.isAtEnd && logoAnimationState.progress > 0.0f) {
            delay(2000)
            onSplashScreenFinished()
        }
    }
}

