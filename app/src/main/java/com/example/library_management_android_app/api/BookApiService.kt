package com.example.library_management_android_app.api

import com.example.library_management_android_app.data_models.BookDataModel.UpdateBook.Updation_Response
import com.example.library_management_android_app.data_models.BookDataModel.AddBook.add_book_request
import com.example.library_management_android_app.data_models.BookDataModel.AddBook.add_book_response
import com.example.library_management_android_app.data_models.BookDataModel.GetAllBooks.getallbook_response
import com.example.library_management_android_app.data_models.BookDataModel.GetBookById.getbookbyid_response
import com.example.library_management_android_app.data_models.Login.LoginRequest
import com.example.library_management_android_app.data_models.Login.LoginResponse
import com.example.library_management_android_app.data_models.SignUp.SignUpRequest
import com.example.library_management_android_app.data_models.SignUp.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface BookApiService {

//    router.post('/add_book/',bookController.createBook)
//
//    router.get('/get_all_books/',bookController.getallbooks)
//
//    router.delete('/delete_book_id/:id',bookController.deletebookbyid)
//
//    router.get('/get_book_id/:id',bookController.getbookbyid)
//
//    router.put('/update_book_id/:id',bookController.updatebookbyid)

    @DELETE("/api/delete_book_id/{id}")
    suspend fun deletebookbyid(@Path("id") id: String): Response<Updation_Response>

    @POST("/api/add_book/")
    suspend fun add_book(@Body add_book_request: add_book_request): Response<add_book_response>

    @GET("/api/get_book_id/{id}")
    suspend fun getbookbyid(@Path("id") id: String): Response<getbookbyid_response>

    @GET("/api/get_all_books/")
    suspend fun getallbook(): Response<getallbook_response>

    @PUT("/api/update_book_id/{id}")
    suspend fun updatebookbyid(
        @Path("id") id: String,
        @Body add_book_request: add_book_request
    ): Response<Updation_Response>


    @POST("/api/signup/")
    suspend fun signup(@Body signUpRequest: SignUpRequest): Response<SignUpResponse>

    @POST("/api/login/")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

}