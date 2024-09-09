package com.gaby.kingoteka.domain.screens

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
import com.gaby.kingoteka.domain.components.DefaultTopBar
import com.gaby.kingoteka.domain.components.NavegacionInferior
import com.gaby.kingoteka.domain.contents.BooksContent
import com.gaby.kingoteka.domain.viewmodels.BooksViewModel


@Composable
fun BooksScreen(navController: NavHostController, viewModel: BooksViewModel) {

    Scaffold(
        topBar = {  DefaultTopBar(title = "GameOTeka", upAvailable = false, navController = navController, color= Color.White)},
        content = { paddingValues ->
            Column {
                Spacer(modifier = Modifier.height(56.dp))
                BooksContent(paddingValues = paddingValues, navHostController = navController, viewModel = hiltViewModel())
            }
        },
        bottomBar = { NavegacionInferior(navController)  }
    )

}
