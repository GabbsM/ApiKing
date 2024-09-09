package com.gaby.kingoteka

import com.gaby.kingoteka.data.BookResponse
import com.gaby.kingoteka.data.ResponseWrapper
import retrofit2.http.GET

interface ApiService {

    @GET("api/books")
    suspend fun getAllBooks(): List<BookResponse>
}