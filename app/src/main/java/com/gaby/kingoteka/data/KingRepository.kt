package com.gaby.kingoteka.data

import android.util.Log
import com.gaby.kingoteka.ApiService
import com.gaby.kingoteka.domain.model.BookModel
import javax.inject.Inject

class KingRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getBooks(): List<BookModel> {
        val response = apiService.getAllBooks()
        Log.d("KingRepository", "Books fetched: ${response.size}")
        return response.map { it.toDomain() }
    }
}