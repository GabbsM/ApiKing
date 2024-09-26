package com.gaby.kingoteka.books.domain.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaby.kingoteka.books.data.BookApiService
import com.gaby.kingoteka.books.data.BooksRepository
import com.gaby.kingoteka.books.domain.models.BookModelDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class BookDetailsViewModel @Inject constructor(
    private val bookApiService: BookApiService,
    private val repository: BooksRepository
) : ViewModel() {
    private val _bookSelected = MutableStateFlow<BookModelDetail?>(null)
    val bookSelected: StateFlow<BookModelDetail?> = _bookSelected.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    fun fetchBookDetails(bookId: Int) {
        viewModelScope.launch {
            try {
                Log.d("BookDetailsViewModel", "Fetching book details for ID: $bookId")
                val bookDetail = withContext(Dispatchers.IO) {
                    repository.getBookDetails(bookId)
                }
                if (bookDetail != null) {
                    _bookSelected.value = bookDetail
                    Log.d("BookDetailsViewModel", "Book details fetched successfully")
                } else {
                    _errorMessage.value = "Error al obtener respuesta de libros"
                    Log.e("BookDetailsViewModel", "Error fetching book details")
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error al obtener respuesta de libros: ${e.message}"
                Log.e("BookDetailsViewModel", "Exception fetching book details", e)
            }
        }
    }
}

