package com.gaby.kingoteka.domain.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaby.kingoteka.data.books.BooksRepository
import com.gaby.kingoteka.domain.models.BookModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val repository: BooksRepository
) : ViewModel() {

    private val _libro = MutableStateFlow<List<BookModel>>(emptyList())
    val libro: StateFlow<List<BookModel>> = _libro

    init {
        fetchBooks()
    }

    private fun fetchBooks() {
        viewModelScope.launch {
            val books = repository.getBooks()
            Log.d("KingViewModel", "Books emitted: ${books.size}")
            _libro.value = books
        }
    }
}