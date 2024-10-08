package com.gaby.kingoteka.movies.domain.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.gaby.kingoteka.books.domain.content.BooksContent
import com.gaby.kingoteka.books.domain.viewmodels.BooksViewModel
import com.gaby.kingoteka.general_components.BottomBar
import com.gaby.kingoteka.general_components.DefaultTopBar
import com.gaby.kingoteka.movies.domain.content.MoviesContent
import com.gaby.kingoteka.movies.domain.viewmodels.MovieViewModel

@Composable
fun MovieScreen(navController: NavHostController, viewModel: MovieViewModel) {

    Scaffold(topBar = {
        DefaultTopBar(
            title = "King Universe",
            upAvailable = false,
            navController = navController,
            color = Color.White
        )
    }, content = { paddingValues ->
        Column {
            Spacer(modifier = Modifier.height(56.dp))
            MoviesContent(
                paddingValues = paddingValues,
                navHostController = navController,
                viewModel = hiltViewModel()
            )
        }
    }, bottomBar = { BottomBar(navController) })

}