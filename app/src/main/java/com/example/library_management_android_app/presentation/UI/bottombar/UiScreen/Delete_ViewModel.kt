package com.example.library_management_system.presentation.UI.bottombar.UiScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.library_management_android_app.api.RetrofitInstance
import com.example.library_management_android_app.data_models.BookDataModel.UpdateBook.Updation_Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class Delete_ViewModel : ViewModel() {

    private val _deleteresponse = MutableStateFlow<Response<Updation_Response>?>(null)
    val deleteresponse: StateFlow<Response<Updation_Response>?> = _deleteresponse

    fun deletebookbyid(id: String) {
        viewModelScope.launch {
            try {
                val result = RetrofitInstance.api.deletebookbyid(id)
                if (result.isSuccessful) {
                    _deleteresponse.value = result
                    Log.e("API success", ": ${result.body()}")

                } else {
                    Log.e("API failure", "Error occurred: ${result.message().length}")
                }
            } catch (e: Exception) {
                Log.e("API Exception", "Error occurred: ${e.message}")
            }
        }
    }

}