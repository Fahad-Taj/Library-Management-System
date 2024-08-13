package com.example.librarymanagementsystem.models

data class CreateBookRequest(
    val author: String,
    val rating: Float,
    val timesLoaned: Int,
    val title: String
)