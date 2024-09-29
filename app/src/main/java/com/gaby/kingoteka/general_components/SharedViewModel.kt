package com.gaby.kingoteka.general_components

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SharedViewModel : ViewModel() {
    private val _bookStatuses = MutableStateFlow<Map<String, String>>(emptyMap())
    val bookStatuses: StateFlow<Map<String, String>> = _bookStatuses.asStateFlow()

    private val _ratings = MutableStateFlow<Map<String, Float>>(emptyMap())
    val ratings: StateFlow<Map<String, Float>> = _ratings.asStateFlow()

    fun updateBookStatus(bookId: String, status: String) {
        _bookStatuses.update {
            it.toMutableMap().apply { put(bookId, status) }
        }
    }

    fun updateRating(bookId: String, rating: Float) {
        _ratings.update {
            it.toMutableMap().apply { put(bookId, rating) }
        }
    }
}