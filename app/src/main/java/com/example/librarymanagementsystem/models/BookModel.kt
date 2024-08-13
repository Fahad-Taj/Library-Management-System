package com.example.librarymanagementsystem.models

data class BookModel(
    val __v: Int,
    val _id: String,
    val author: String,
    val rating: Float,
    val timesLoaned: Int,
    var title: String
)