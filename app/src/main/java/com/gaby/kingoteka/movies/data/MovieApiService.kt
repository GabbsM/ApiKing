package com.gaby.kingoteka.movies.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiService {

    @GET("api/moviesbyyear")
    suspend fun getMoviesByYear(): List<MovieResponse>

    @GET("api/movies/{id}")
    suspend fun getMovieDetails(@Path("id") id: Int): Response<MovieResponse>
}