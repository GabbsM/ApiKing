package com.gaby.kingoteka.movies.domain.models

data class MovieModelDetail(
    val id: Int,
    val title: String?,
    val adapted_from: String?,
    val director: String?,
    val url_image: String,
    val tipo:String,
    val year: Int,
    val notes: String?
) {
    fun toDomainDetail(): MovieModelDetail? {
        return MovieModelDetail(
            id = id,
            title = title ?: "Titulo Desconocido",
            adapted_from = adapted_from ?: "Desconocido",
            director = director ?: "Desconocido",
            url_image = url_image,
            year = year,
            notes = notes ?: "",
            tipo = tipo
        )
    }
}

