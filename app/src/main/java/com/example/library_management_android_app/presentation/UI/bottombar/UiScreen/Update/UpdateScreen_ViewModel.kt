package com.example.library_management_system.presentation.UI.bottombar.UiScreen.Update

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.library_management_android_app.api.RetrofitInstance
import com.example.library_management_android_app.data_models.BookDataModel.UpdateBook.Updation_Response
import com.example.library_management_android_app.data_models.BookDataModel.AddBook.add_book_request
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class UpdateScreen_ViewModel : ViewModel() {


    private val _updatebookresponse = MutableStateFlow<Response<Updation_Response>?>(null)
    val updatebookresponse: StateFlow<Response<Updation_Response>?> = _updatebookresponse

    fun updatebook(id:String,add_book_request: add_book_request) {
        viewModelScope.launch {
            val result= RetrofitInstance.api.updatebookbyid(id,add_book_request)
            try{
                if (result.isSuccessful) {
                    _updatebookresponse.value=result
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