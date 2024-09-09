package com.gaby.kingoteka.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gaby.kingoteka.domain.screens.SearchScreen
import com.gaby.kingoteka.domain.screens.StephenVerseScreen
import com.gaby.kingoteka.domain.screens.UserScreen
import com.gaby.kingoteka.domain.screens.BooksScreen


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
    }
}