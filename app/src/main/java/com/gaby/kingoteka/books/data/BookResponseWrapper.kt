package com.gaby.kingoteka.books.data

import com.gaby.kingoteka.books.domain.models.BookModel
import com.gaby.kingoteka.books.domain.models.BookModelDetail
import com.google.gson.annotations.SerializedName

data class ResponseWrapper(

    val books: List<BookResponse>


)

data class BookResponse(
    @SerializedName("genre") val genero: String?,
    @SerializedName("id") val id: Int,
    @SerializedName("notes") val notas: String,
    @SerializedName("original_title") val titulo_original: String?,
    @SerializedName("pages") val paginas: Int,
    @SerializedName("sinopsis") val sinopsis: String,
    @SerializedName("spanish_title") val titulo: String?,
    @SerializedName("url_image") val url_image: String,
    @SerializedName("year") val anio: Int
) {
    fun toDomain(): BookModel {
        return BookModel(
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

    fun toDomainDetail(): BookModelDetail {
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


