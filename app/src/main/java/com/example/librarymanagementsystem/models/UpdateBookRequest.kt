package com.example.librarymanagementsystem.models

data class UpdateBookRequest(
    val author: String,
    val id: String,
    val rating: Float,
    val timesLoaned: Int,
    val title: String
)