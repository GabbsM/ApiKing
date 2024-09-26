package com.gaby.kingoteka.general_components

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SharedViewModel : ViewModel() {
    private val _selectedOptions = MutableStateFlow<Map<String, String>>(emptyMap())
    val selectedOptions: StateFlow<Map<String, String>> = _selectedOptions

    private val _ratings = MutableStateFlow<Map<String, Float>>(emptyMap())
    val ratings: StateFlow<Map<String, Float>> = _ratings

    fun updateSelectedOption(bookId: String, option: String) {
        _selectedOptions.value = _selectedOptions.value.toMutableMap().apply { put(bookId, option) }
    }

    fun updateRating(bookId: String, rating: Float) {
        _ratings.value = _ratings.value.toMutableMap().apply { put(bookId, rating) }
    }
}