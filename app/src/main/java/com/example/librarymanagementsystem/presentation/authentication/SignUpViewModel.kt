package com.example.librarymanagementsystem.presentation.authentication

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.librarymanagementsystem.api.RetrofitInstance
import com.example.librarymanagementsystem.models.LoginSignUpRequest
import com.example.librarymanagementsystem.models.LoginSignupResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class SignUpViewModel: ViewModel() {


    private val _response = MutableStateFlow<Response<LoginSignupResponse>?>(null)
    val response = _response.asStateFlow()

    var email = mutableStateOf("")
    var password = mutableStateOf("")

    fun signup(){
        viewModelScope.launch {
            val result = RetrofitInstance.api.signup(
                LoginSignUpRequest(email = email.value, password = password.value)
            )
            _response.value = result
        }
    }


}