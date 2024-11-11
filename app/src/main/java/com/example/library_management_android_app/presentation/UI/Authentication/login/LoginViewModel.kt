package com.example.library_management_android_app.presentation.UI.Authentication.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.library_management_android_app.api.RetrofitInstance
import com.example.library_management_android_app.data_models.Login.LoginRequest
import com.example.library_management_android_app.data_models.Login.LoginResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Response

class LoginViewModel : ViewModel() {

    private val _loginresponse = MutableStateFlow<Response<LoginResponse>?>(null)
    val loginresponse: StateFlow<Response<LoginResponse>?> = _loginresponse

    private val _isLoginSuccessful = MutableStateFlow("false")
    val isLoginSuccessful: StateFlow<String> = _isLoginSuccessful

    fun login(loginRequest: LoginRequest) {
        viewModelScope.launch {
            try {
                val result = RetrofitInstance.api.login(loginRequest)
                if (result.isSuccessful) {
                    _loginresponse.value = result
                    _isLoginSuccessful.value = "true"
                    Log.d("Login Success", "Login successful: ${result.body()?.uid}")
                } else {
                    var errorBody = result.errorBody()?.string()
                    errorBody?.let {
                        val jsonObject = JSONObject(it)
                        val errorMessage = jsonObject.getString("error")
                        Log.e("Login Failure 1", "Error occurred 1: $errorMessage")
                        _isLoginSuccessful.value = errorMessage
                    }
                    Log.e("Login Failure 2", "Error occurred 2 : $errorBody")
                }
            } catch (e: Exception) {
                _isLoginSuccessful.value = "An error occurred: ${e.message}"
                Log.e("API Exception", "Error occurred: ${e.message}")
            }
        }
    }

    fun resetState() {
        _loginresponse.value = null
        _isLoginSuccessful.value = "false"
    }
}
