package com.gaby.kingoteka.movies.domain.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaby.kingoteka.books.domain.models.BookModelDetail
import com.gaby.kingoteka.movies.data.MoviesRepository
import com.gaby.kingoteka.movies.domain.models.MovieModelDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository:MoviesRepository):ViewModel(){

    private val _movie = MutableStateFlow<List<MovieModelDetail>>(emptyList())
    val movie: StateFlow<List<MovieModelDetail>> = _movie

    init {
        fetchMovies()
    }

     fun fetchMovies() {
        viewModelScope.launch {
            val movies = repository.getMoviesByYear()
            if (movies != null) {
                _movie.value = movies
            } else {
                // Handle error case
            }
        }
    }




}