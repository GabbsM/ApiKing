package com.gaby.kingoteka.movies.domain.models

data class MovieModel(

    val id: Int,
    val title: String?,
    val adapted_from: String?,
    val director: String?,
    val url_image: String,
    val tipo:String,
    val year: Int,
    val notes: String
)
