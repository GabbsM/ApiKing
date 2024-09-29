package com.gaby.kingoteka.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gaby.kingoteka.books.domain.screens.BookDetailsScreen
import com.gaby.kingoteka.books.domain.screens.BooksScreen
import com.gaby.kingoteka.search.screens.SearchScreen
import com.gaby.kingoteka.stephenVerse.screens.StephenVerseScreen
import com.gaby.kingoteka.user.screens.UserScreen
import com.gaby.kingoteka.books.domain.viewmodels.BookDetailsViewModel
import com.gaby.kingoteka.books.domain.viewmodels.BooksViewModel
import com.gaby.kingoteka.general_components.SharedViewModel
import com.gaby.kingoteka.movies.domain.screens.MovieDetailsScreen
import com.gaby.kingoteka.movies.domain.screens.MovieScreen
import com.gaby.kingoteka.movies.domain.viewmodels.MovieDetailsViewModel

@Composable
fun AppNavigation(navController: NavHostController) {
    val viewModel: BooksViewModel = hiltViewModel<BooksViewModel>()
    val sharedViewModel: SharedViewModel = hiltViewModel<SharedViewModel>()
    NavHost(navController = navController, startDestination = "books") {
        composable("books") {
            BooksScreen(navController, viewModel = viewModel, sharedViewModel = sharedViewModel)
        }
        composable("movies"){
            MovieScreen(navController, viewModel = hiltViewModel())
        }
        composable("search") {
            SearchScreen()
        }
        composable("sv") {
            StephenVerseScreen()
        }
        composable("user") {
            UserScreen()
        }

        composable(AppScreen.MovieDetails.route + "/{id}") { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            if (movieId != null) {
                val viewModel: MovieDetailsViewModel = hiltViewModel()
                viewModel.fetchMovieDetails(movieId)
                MovieDetailsScreen(
                    navController = navController,
                    movieId = movieId,
                    viewModel = viewModel
                )
            } else {
                //TODO
            }
        }
        composable(AppScreen.BookDetails.route + "/{id}") { backStackEntry ->
            val bookId = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            if (bookId != null) {
                val viewModel: BookDetailsViewModel = hiltViewModel()
                viewModel.fetchBookDetails(bookId)
                BookDetailsScreen(
                    navController = navController,
                    bookId = bookId,
                    viewModel = viewModel,
                    sharedViewModel = sharedViewModel
                )
            } else {
                //TODO
            }
        }
    }
}