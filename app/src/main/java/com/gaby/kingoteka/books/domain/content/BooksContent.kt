package com.gaby.kingoteka.books.domain.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.gaby.kingoteka.general_components.SharedViewModel
import com.gaby.kingoteka.books.domain.viewmodels.BooksViewModel
import com.gaby.kingoteka.navigation.AppScreen

@Composable
fun BooksContent(
    paddingValues: PaddingValues,
    navHostController: NavHostController,
    viewModel: BooksViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel = hiltViewModel()
) {
    val libro by viewModel.libro.collectAsState()
    val selectedOptions by sharedViewModel.selectedOptions.collectAsState()
    val ratings by sharedViewModel.ratings.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize().background(Color.White)
    ) {
        LazyColumn(modifier = Modifier.align(Alignment.Center)) {
            items(libro) { item ->
                val selectedOption = selectedOptions[item.id.toString()] ?: "Pendiente"
                val rating = ratings[item.id.toString()] ?: 1f
                val textColor = when (selectedOption) {
                    "Pendiente" -> Color(0xFF7626E9)
                    "Leyendo" -> Color(0xFF26E99F)
                    "Leído" -> Color(0xFFFFA500)
                    else -> Color.Gray
                }

                Row(
                    modifier = Modifier.padding(16.dp).background(Color.White)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(item.url_image),
                        contentDescription = "imagen no disponible",
                        modifier = Modifier.height(150.dp).align(Alignment.CenterVertically)
                            .clickable(onClick = {
                                navHostController.navigate(AppScreen.BookDetails.route + "/${item.id}")
                            })
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                        Text(
                            text = item.titulo,
                            color = Color.Black,
                            fontSize = 24.sp,
                            modifier = Modifier.align(Alignment.Start)
                        )
                        Text(
                            text = item.titulo_original,
                            color = Color.Black,
                            fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                            modifier = Modifier.align(Alignment.Start)
                        )
                        Text(
                            text = item.anio.toString(),
                            color = Color.Black,
                            fontSize = 15.sp,
                            modifier = Modifier.align(Alignment.Start)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = selectedOption,
                            color = textColor,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.align(Alignment.Start)
                        )
                        StarRatingBar(
                            bookId = item.id.toString(),
                            initialRating = rating,
                            sharedViewModel = sharedViewModel
                        )
                    }
                }
                HorizontalDivider(thickness = 1.dp, color = Color.Gray)
            }
        }
    }
}