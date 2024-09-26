package com.gaby.kingoteka.movies.data

import com.gaby.kingoteka.movies.domain.models.MovieModel
import com.gaby.kingoteka.movies.domain.models.MovieModelDetail
import com.google.gson.annotations.SerializedName

data class MovieResponseWrapper(

    val movies: List<MovieResponse>
)

data class MovieResponse(

    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String?,
    @SerializedName("adapted_from") val adapted_from: String?,
    @SerializedName("director") val director: String?,
    @SerializedName("url_image") val url_image: String,
    @SerializedName("tipo") val tipo: String,
    @SerializedName("year") val year: Int,
    @SerializedName("notes") val notes: String

) {
    fun toDomain(): MovieModel {
        return MovieModel(
            id = id,
            title = title ?: "Titulo Desconocido",
            adapted_from = adapted_from ?: "Desconocido",
            director = director ?: "Desconocido",
            url_image = url_image,
            year = year,
            tipo = tipo,
            notes = notes,

            )
    }

    fun toDomainDetail(): MovieModelDetail {
        return MovieModelDetail(
            id = id,
            title = title ?: "Titulo Desconocido",
            adapted_from = adapted_from ?: "Desconocido",
            director = director ?: "Desconocido",
            url_image = url_image,
            year = year,
            notes = notes,
            tipo = tipo
        )
    }
}


