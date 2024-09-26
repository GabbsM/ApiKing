package com.gaby.kingoteka.movies.domain.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.gaby.kingoteka.books.domain.content.BookDetailsContent
import com.gaby.kingoteka.books.domain.viewmodels.BookDetailsViewModel
import com.gaby.kingoteka.general_components.BottomBar
import com.gaby.kingoteka.general_components.DefaultTopBar
import com.gaby.kingoteka.general_components.SharedViewModel
import com.gaby.kingoteka.movies.domain.content.MovieDetailsContent
import com.gaby.kingoteka.movies.domain.viewmodels.MovieDetailsViewModel
import com.gaby.kingoteka.movies.domain.viewmodels.MovieViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MovieDetailsScreen(
    navController: NavHostController,
    movieId: Int,
    viewModel: MovieDetailsViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel = hiltViewModel()
) {
    // Fetch book details
    viewModel.fetchMovieDetails(movieId)
    val movieSelected by viewModel.movieSelected.collectAsState()

    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "GameOTeka",
                upAvailable = false,
                navController = navController,
                color = Color.White
            )
        },
        content = {
            Column {
                Spacer(modifier = Modifier.height(56.dp)) // Añade un espacio de 56.dp que es la altura típica de una TopBar

                MovieDetailsContent(
                    movieSelected = movieSelected,
                    sharedViewModel = sharedViewModel,
                    navHostController = navController
                )
            }
        },
        bottomBar = { BottomBar(navController) }
    )
}