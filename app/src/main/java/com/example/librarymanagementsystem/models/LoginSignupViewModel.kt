package com.example.librarymanagementsystem.models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Response

class LoginSignupViewModel: ViewModel() {

    var username = mutableStateOf("")
    var password = mutableStateOf("")

    fun login(){

    }

}