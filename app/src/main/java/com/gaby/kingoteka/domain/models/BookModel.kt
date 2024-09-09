package com.gaby.kingoteka.domain.models

data class BookModel(
    val año: Int,
    val genero: String,
    val notas: String,
    val paginas: Int,
    val titulo: String,
    val titulo_original: String,
    val id: Int,
    val url_image:String,
    val sinopsis:String
)
