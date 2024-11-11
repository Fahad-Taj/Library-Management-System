package com.example.library_management_android_app.presentation.UI.bottombar.UiScreen.HomeScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.library_management_android_app.api.RetrofitInstance
import com.example.library_management_android_app.data_models.BookDataModel.GetAllBooks.getallbook_response
import com.example.library_management_android_app.data_models.BookDataModel.GetBookById.getbookbyid_response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class MyScreenViewModel : ViewModel() {

    private val _bookByIdResponse = MutableStateFlow<Response<getbookbyid_response>?>(null)
    val bookByIdResponse: StateFlow<Response<getbookbyid_response>?> = _bookByIdResponse

    private val _allBooksResponse = MutableStateFlow<Response<getallbook_response>?>(null)
    val allBooksResponse: StateFlow<Response<getallbook_response>?> = _allBooksResponse

    fun getBookById(id: String) {
        viewModelScope.launch {
            _bookByIdResponse.value = safeApiCall {
                RetrofitInstance.api.getbookbyid(id)
            }
            _allBooksResponse.value=null
        }
    }

    fun getAllBooks() {
        viewModelScope.launch {
            _allBooksResponse.value = safeApiCall {
                RetrofitInstance.api.getallbook()
            }
            _bookByIdResponse.value=null
        }
    }

    private suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Response<T>? {
        return try {
            val response = apiCall()
            if (response.isSuccessful) {
                Log.d("API Success", response.message())
                response
            } else {
                Log.e("API Error", "Unsuccessful response: ${response.message()}")
                null
            }
        } catch (e: Exception) {
            Log.e("API Exception", "Error occurred: ${e.message}")
            null
        }
    }
}
