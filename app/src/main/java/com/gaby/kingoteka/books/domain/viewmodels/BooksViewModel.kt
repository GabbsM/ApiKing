package com.gaby.kingoteka.books.domain.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaby.kingoteka.books.data.BooksRepository
import com.gaby.kingoteka.books.domain.models.BookModelDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(private val repository: BooksRepository) : ViewModel() {

    private val _libros = MutableStateFlow<List<BookModelDetail>>(emptyList())
    val libros: StateFlow<List<BookModelDetail>> = _libros

    init {
        fetchBooks()
    }

    private fun fetchBooks() {
        viewModelScope.launch {
            val books = repository.getBooksByYear()
            if (books != null) {
                _libros.value = books
            } else {
                // Handle error case
            }
        }
    }

}