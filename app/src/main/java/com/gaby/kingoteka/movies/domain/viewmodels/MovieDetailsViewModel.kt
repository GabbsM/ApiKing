package com.gaby.kingoteka.movies.domain.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaby.kingoteka.books.domain.models.BookModelDetail
import com.gaby.kingoteka.movies.data.MovieApiService
import com.gaby.kingoteka.movies.data.MoviesRepository
import com.gaby.kingoteka.movies.domain.models.MovieModelDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val apiService: MovieApiService,
    private val repository: MoviesRepository
): ViewModel() {

    private val _movieSelected = MutableStateFlow<MovieModelDetail?>(null)
    val movieSelected: StateFlow<MovieModelDetail?> = _movieSelected.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    fun fetchMovieDetails(movieId: Int) {
        viewModelScope.launch {
            try {
                Log.d("MovieDetailsViewModel", "Fetching movie details for ID: $movieId")
                val movieDetail = withContext(Dispatchers.IO) {
                    repository.getMovieDetails(movieId)
                }
                if (movieDetail != null) {
                    _movieSelected.value = movieDetail
                    Log.d("MovieDetailsViewModel", "Movie details fetched successfully")
                } else {
                    _errorMessage.value = "Error al obtener respuesta de películas"
                    Log.e("MovieDetailsViewModel", "Error fetching movie details")
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error al obtener respuesta de películas: ${e.message}"
                Log.e("MovieDetailsViewModel", "Exception fetching movie details", e)
            }
        }
    }
}