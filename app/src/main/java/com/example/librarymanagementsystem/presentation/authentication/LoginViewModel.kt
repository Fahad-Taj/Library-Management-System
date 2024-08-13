package com.example.librarymanagementsystem.presentation.authentication

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.librarymanagementsystem.api.RetrofitInstance
import com.example.librarymanagementsystem.models.LoginSignUpRequest
import com.example.librarymanagementsystem.models.LoginSignupResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginViewModel: ViewModel() {


    private val _response = MutableStateFlow<Response<LoginSignupResponse>?>(null)
    val response = _response.asStateFlow()

    var email = mutableStateOf("")
    var password = mutableStateOf("")

    fun login(){
        viewModelScope.launch {
            val result = RetrofitInstance.api.login(
                LoginSignUpRequest(email = email.value, password = password.value)
            )
            _response.value = result
        }
    }


}