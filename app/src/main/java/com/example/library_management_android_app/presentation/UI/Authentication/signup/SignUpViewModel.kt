package com.example.library_management_android_app.presentation.UI.Authentication.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.library_management_android_app.api.RetrofitInstance
import com.example.library_management_android_app.data_models.SignUp.SignUpRequest
import com.example.library_management_android_app.data_models.SignUp.SignUpResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Response


class SignUpViewModel : ViewModel() {

    private val _signresponse = MutableStateFlow<Response<SignUpResponse>?>(null)
    val signupresponse: StateFlow<Response<SignUpResponse>?> = _signresponse

    private val _isSignUpSuccessful = MutableStateFlow("false")
    val isSignUpSuccessful: StateFlow<String> = _isSignUpSuccessful

    fun sign(signUpRequest: SignUpRequest) {
        viewModelScope.launch {
            val result = RetrofitInstance.api.signup(signUpRequest)
            try {
                if (result.isSuccessful) {
                    _signresponse.value = result
                    _isSignUpSuccessful.value = "true"
                    Log.d("Login  Success", result.message().toString())
                    Log.e("success", result.body()?.uid.toString())

                } else {
                    var errorBody = result.errorBody()?.string()
                    errorBody?.let {
                        val jsonObject = JSONObject(it)
                        val errorMessage = jsonObject.getString("error")
                        Log.e("Login Failure", "Error occurred: $errorMessage")
                        _isSignUpSuccessful.value = errorMessage
                    }
                }
            } catch (e: Exception) {
                Log.e("API Exception", "Error occurred: ${e.message}")
                _isSignUpSuccessful.value = e.message ?: "Unknown error occurred" // Set the exception message
            }
        }

    }
    fun resetState() {
        _signresponse.value = null
        _isSignUpSuccessful.value = "false"
    }
}
