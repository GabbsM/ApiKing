package com.gaby.kingoteka.books.data



import com.gaby.kingoteka.books.domain.models.BookModelDetail
import javax.inject.Inject

class BooksRepository @Inject constructor(private val bookApiService: BookApiService) {

    suspend fun getBooksByYear(): List<BookModelDetail> {
        val response = bookApiService.getBooksByYear()
        return response.map { it.toDomainDetail() }
    }

    suspend fun getBookDetails(id: Int): BookModelDetail? {
        val response = bookApiService.getBookDetails(id)
        return if (response.isSuccessful) {
            response.body()?.toDomainDetail()
        } else {
            null
        }
    }
}