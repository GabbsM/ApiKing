package com.gaby.kingoteka.movies.data

import com.gaby.kingoteka.movies.domain.models.MovieModelDetail
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val apiservice: MovieApiService) {

    suspend fun getMoviesByYear(): List<MovieModelDetail> {
        val response = apiservice.getMoviesByYear()
        return response.map { it.toDomainDetail() }
    }

    suspend fun getMovieDetails(id: Int): MovieModelDetail? {
        val response = apiservice.getMovieDetails(id)
        return if (response.isSuccessful) {
            response.body()?.toDomainDetail()
        } else {
            null
        }
    }
}