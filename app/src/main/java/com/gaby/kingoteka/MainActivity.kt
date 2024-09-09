package com.gaby.kingoteka

import com.gaby.kingoteka.domain.screens.BooksScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.gaby.kingoteka.domain.viewmodels.BooksViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val viewModel = hiltViewModel<BooksViewModel>()
            MaterialTheme {
                Surface {
                    BooksScreen(navController = navController, viewModel = viewModel)
                }
            }
        }
    }
}


