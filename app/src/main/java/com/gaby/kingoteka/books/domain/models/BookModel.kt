package com.gaby.kingoteka.books.domain.models

data class BookModel(
    val anio: Int,
    val genero: String,
    val notas: String,
    val paginas: Int,
    val titulo: String,
    val titulo_original: String,
    val id: Int,
    val url_image:String,
    val sinopsis:String
)
