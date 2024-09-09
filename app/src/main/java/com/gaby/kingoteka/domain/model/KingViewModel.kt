package com.gaby.kingoteka.domain.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gaby.kingoteka.data.KingRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class KingViewModel @Inject constructor(
    private val repository: KingRepository
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