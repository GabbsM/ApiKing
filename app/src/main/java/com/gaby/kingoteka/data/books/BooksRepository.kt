package com.gaby.kingoteka.data.books

import android.util.Log
import com.gaby.kingoteka.data.ApiService
import com.gaby.kingoteka.domain.models.BookModel
import javax.inject.Inject

class BooksRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getBooks(): List<BookModel> {
        val response = apiService.getAllBooks()
        Log.d("KingRepository", "Books fetched: ${response.size}")
        return response.map { it.toDomain() }
    }
}