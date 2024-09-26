package com.gaby.kingoteka.general_components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.gaby.kingoteka.books.domain.models.BookModelDetail
import com.gaby.kingoteka.movies.domain.models.MovieModelDetail
import com.gaby.kingoteka.navigation.AppScreen

@Composable
fun CardMovie(movie: MovieModelDetail, navController: NavHostController, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .width(140.dp)
            .background(Color.White)
            .size(140.dp)
            .shadow(5.dp)
            .clickable {
                navController.navigate(AppScreen.BookDetails.route + "/${movie.id}")
            }
    ) {
        Column {
            StartImage(image = movie.url_image)
        }
    }
}