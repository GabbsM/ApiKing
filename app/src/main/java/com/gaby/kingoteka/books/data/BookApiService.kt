package com.gaby.kingoteka.books.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BookApiService {

    @GET("api/booksbyyear")
    suspend fun getBooksByYear(): List<BookResponse>

    @GET("api/books/{id}")
    suspend fun getBookDetails(@Path("id") id: Int): Response<BookResponse>

}