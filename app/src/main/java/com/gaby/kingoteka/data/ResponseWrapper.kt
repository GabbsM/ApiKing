package com.gaby.kingoteka.data

import com.gaby.kingoteka.domain.model.BookModel
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
    @SerializedName("year") val año: Int
) {
    fun toDomain(): BookModel {
        return BookModel(
            año = año,
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
