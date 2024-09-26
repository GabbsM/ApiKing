package com.gaby.kingoteka.books.domain.models

data class BookModelDetail(
    val anio: Int,
    val genero: String,
    val notas: String,
    val paginas: Int,
    val titulo: String,
    val titulo_original: String,
    val id: Int,
    val url_image: String,
    val sinopsis: String
) {
    fun toDomainDetail(): BookModelDetail? {
        return BookModelDetail(
            anio = anio,
            genero = genero ?: "Desconocido",
            notas = notas,
            paginas = paginas,
            titulo = titulo ?: "Titulo Desconocido",
            titulo_original = titulo_original ?: "Desconocido",
            id = id,
            url_image = url_image,
            sinopsis = sinopsis
        )
    }
}