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

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "books") {
        composable("books") {
            BooksScreen(navController, viewModel = hiltViewModel())
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
        composable(AppScreen.BookDetails.route + "/{id}") { backStackEntry ->
            val bookId = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            if (bookId != null) {
                val viewModel: BookDetailsViewModel = hiltViewModel()
                viewModel.fetchBookDetails(bookId)
                BookDetailsScreen(
                    navController = navController,
                    bookId = bookId,
                    viewModel = viewModel
                )
            } else {
                // Handle the case where bookId is not a valid number
            }
        }
    }
}