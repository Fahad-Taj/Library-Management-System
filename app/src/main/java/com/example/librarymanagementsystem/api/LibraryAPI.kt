package com.example.librarymanagementsystem.api

import com.example.librarymanagementsystem.models.ApiResponse
import com.example.librarymanagementsystem.models.BookModel
import com.example.librarymanagementsystem.models.CreateBookRequest
import com.example.librarymanagementsystem.models.LoginSignUpRequest
import com.example.librarymanagementsystem.models.LoginSignupResponse
import com.example.librarymanagementsystem.models.UpdateBookRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

// API interface
interface LibraryAPI {

    @GET("/getAllBooks")
    suspend fun GetAllBooks(): Response<List<BookModel>>

    @GET("/getBookById/{id}")
    suspend fun GetBookById(
        @Path("id") id: String
    ): Response<BookModel>

    @POST("/createBook")
    suspend fun CreateBook(
        @Body book: CreateBookRequest
    ): Response<ApiResponse>

    @DELETE("/deleteBookById/{id}")
    suspend fun DeleteBookById(
        @Path("id") id: String
    ): Response<ApiResponse>

    @PUT("/updateBookById")
    suspend fun UpdateBook(
        @Body updatedBookModel: UpdateBookRequest
    ): Response<String>

    @POST("/signin")
    suspend fun login(
        @Body loginBody: LoginSignUpRequest
    ): Response<LoginSignupResponse>

    @POST("/signup")
    suspend fun signup(
        @Body signupBody: LoginSignUpRequest
    ): Response<LoginSignupResponse>

}