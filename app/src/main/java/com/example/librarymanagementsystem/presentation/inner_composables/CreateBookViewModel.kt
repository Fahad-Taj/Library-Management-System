package com.example.librarymanagementsystem.presentation.inner_composables

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.librarymanagementsystem.api.RetrofitInstance
import com.example.librarymanagementsystem.models.BookModel
import com.example.librarymanagementsystem.models.CreateBookRequest
import com.example.librarymanagementsystem.models.UpdateBookRequest
import kotlinx.coroutines.launch
import okhttp3.internal.http.RetryAndFollowUpInterceptor
import kotlin.contracts.Effect

class CreateBookViewModel: ViewModel() {

    var id: MutableState<String> = mutableStateOf("")
    var title: MutableState<String> = mutableStateOf("")
    var author: MutableState<String> = mutableStateOf("")
    var rating: MutableState<String> = mutableStateOf("")
    var timesLoaned: MutableState<String> = mutableStateOf("")

    fun updateId(updatedId: String){
        id.value = updatedId
    }

    fun updateBook(){
        viewModelScope.launch {
            try {
                val result = RetrofitInstance.api.UpdateBook(
                    updatedBookModel = UpdateBookRequest(
                        author = author.value,
                        id = id.value,
                        rating = rating.value.toFloat(),
                        timesLoaned = timesLoaned.value.toInt(),
                        title = title.value
                    )
                )
            } catch(e: Exception){
                e.printStackTrace()
            }
        }
    }

    fun createBook(context: Context){
        viewModelScope.launch {
            try {
                val result = RetrofitInstance.api.CreateBook(
                    CreateBookRequest(
                        author = author.value,
                        title = title.value,
                        rating = rating.value.toFloat(),
                        timesLoaned = timesLoaned.value.toInt()
                    )
                )
                if(result.isSuccessful){
                    Toast.makeText(context, "Book Created successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Error! Couldn't create book", Toast.LENGTH_SHORT).show()
                }
            } catch(e: Exception){
                e.printStackTrace()
            }
        }
    }
}