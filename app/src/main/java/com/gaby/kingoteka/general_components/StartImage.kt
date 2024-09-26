package com.gaby.kingoteka.general_components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberAsyncImagePainter

@Composable
fun StartImage(image: String, modifier: Modifier = Modifier) {
    val imagen = rememberAsyncImagePainter(model = image)

    Image(
        painter = imagen,
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = modifier
    )
}