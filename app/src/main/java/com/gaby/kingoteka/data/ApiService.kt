package com.gaby.kingoteka.data

import com.gaby.kingoteka.data.books.BookResponse
import retrofit2.http.GET

interface ApiService {

    @GET("api/books")
    suspend fun getAllBooks(): List<BookResponse>
}