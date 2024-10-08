package com.example.librarymanagementsystem

import android.content.Context
import android.os.Bundle
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.librarymanagementsystem.api.PASSWORD_KEY
import com.example.librarymanagementsystem.api.RetrofitInstance
import com.example.librarymanagementsystem.api.USER_NAME_KEY
import com.example.librarymanagementsystem.api.dataStore
import com.example.librarymanagementsystem.models.LoginSignUpRequest
import com.example.librarymanagementsystem.models.LoginSignupResponse
import com.example.librarymanagementsystem.presentation.MainScreenComposable
import com.example.librarymanagementsystem.presentation.authentication.AuthenticationGraph
import com.example.librarymanagementsystem.ui.theme.LibraryManagementSystemTheme
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition { false }
        enableEdgeToEdge()
        setContent {
                AuthenticationGraph()
             }
        }
}

@Composable
fun SplashScreen(onSplashScreen: ()->Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash))
        val logoaAnimationState = animateLottieCompositionAsState(composition = composition)

        LottieAnimation(composition = composition, progress = logoaAnimationState.progress)

        LaunchedEffect(logoaAnimationState.progress>0.8f && logoaAnimationState.isAtEnd) {
            delay(2000)
            onSplashScreen()
        }
    }

}
