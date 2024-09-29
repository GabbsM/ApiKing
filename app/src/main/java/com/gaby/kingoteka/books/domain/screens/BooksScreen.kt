package com.gaby.kingoteka.books.domain.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.gaby.kingoteka.general_components.BottomBar
import com.gaby.kingoteka.general_components.DefaultTopBar
import com.gaby.kingoteka.books.domain.content.BooksContent
import com.gaby.kingoteka.books.domain.viewmodels.BooksViewModel
import com.gaby.kingoteka.general_components.SharedViewModel


@Composable
fun BooksScreen(navController: NavHostController,
                viewModel: BooksViewModel,
                sharedViewModel: SharedViewModel) {
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
            BooksContent(
                paddingValues = paddingValues,
                navHostController = navController,
                viewModel = viewModel,
                sharedViewModel = sharedViewModel
            )
        }
    }, bottomBar = { BottomBar(navController) })

}