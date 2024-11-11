package com.example.library_management_android_app.presentation.UI.bottombar.UiScreen.Create

import com.example.library_management_android_app.data_models.BookDataModel.AddBook.add_book_request
import com.example.library_management_android_app.data_models.BookDataModel.AddBook.add_book_response
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.library_management_android_app.api.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class CreateBook_ViewModel : ViewModel() {


    private val _createbookresponse = MutableStateFlow<Response<add_book_response>?>(null)
    val createbookresponse: StateFlow<Response<add_book_response>?> = _createbookresponse

    fun addbook(add_book_request: add_book_request) {
        viewModelScope.launch {
            val result= RetrofitInstance.api.add_book(add_book_request =add_book_request)
            try{
                if (result.isSuccessful) {
                    _createbookresponse.value=result
                    Log.d("API Success", result.message())
                } else {
                    Log.e("API failure", "Error occurred: ${result.message().length}")
                }
            }catch (e:Exception){
                Log.e("API Exception", "Error occurred: ${e.message}")
            }
        }
    }
}
