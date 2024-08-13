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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel: ViewModel() {

    private val _booksFlow = MutableStateFlow<List<BookModel>>(emptyList())
    val booksFlow = _booksFlow.asStateFlow()

    var id: MutableState<String> = mutableStateOf("")

    fun updateId(updatedId: String){
        id.value = updatedId
    }

    fun GetAllBooks(){
        viewModelScope.launch {
            try {
                val result = RetrofitInstance.api.GetAllBooks()
                if(result.isSuccessful) _booksFlow.value = result.body()!!
            } catch(e: Exception){
                e.printStackTrace()
            }
        }
    }

    fun getBookById(){
        viewModelScope.launch {
            try{
                val result = RetrofitInstance.api.GetBookById(id.value)
                _booksFlow.value = listOf(result.body()!!)
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    fun deleteBook(id: String, context: Context){
        viewModelScope.launch {
            try {
                val result = RetrofitInstance.api.DeleteBookById(id)
                if(result.isSuccessful){
                    Toast.makeText(context, "Book deleted successfully", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

}