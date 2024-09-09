package com.gaby.kingoteka.domain.contents

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.gaby.kingoteka.domain.viewmodels.BooksViewModel

@Composable
fun BooksContent(
    paddingValues: PaddingValues,
    navHostController: NavHostController,
    viewModel: BooksViewModel = hiltViewModel()
    ) {
    val libro by viewModel.libro.collectAsState()
    val options = listOf("Pendiente", "Leyendo", "Leído")
    var expandedIndex by remember { mutableStateOf(-1) }
    var selectedOptions by remember { mutableStateOf(mutableMapOf<Int, String>()) }

    Box(
        modifier = Modifier.fillMaxSize().background(Color.White)
    ) {
        LazyColumn(modifier = Modifier.align(Alignment.Center)) {
            items(libro) { item ->
                val index = libro.indexOf(item)
                val selectedOption = selectedOptions[index] ?: options[0]

                Row(
                    modifier = Modifier.padding(16.dp).background(Color.White)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(item.url_image),
                        contentDescription = "imagen no disponible",
                        modifier = Modifier.height(125.dp).align(Alignment.CenterVertically)
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
                            text = item.año.toString(),
                            color = Color.Black,
                            fontSize = 15.sp,
                            modifier = Modifier.align(Alignment.Start)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Box {
                            Row(modifier = Modifier.clickable {
                                expandedIndex = if (expandedIndex == index) -1 else index
                            }.background(Color.Blue).padding(8.dp)) {
                                Text(
                                    text = selectedOption, color = Color.White, fontSize = 15.sp
                                )
                                Spacer(modifier = Modifier.width(4.dp))

                                Spacer(modifier = Modifier.width(8.dp))
                                Row {
                                    repeat(5) {
                                        Icon(
                                            imageVector = Icons.Default.Star,
                                            contentDescription = "Estrella vacía",
                                            tint = Color.White,
                                            modifier = Modifier.size(24.dp)
                                        )
                                    }
                                }
                            }
                            DropdownMenu(expanded = expandedIndex == index,
                                onDismissRequest = { expandedIndex = -1 }) {
                                options.forEach { option ->
                                    DropdownMenuItem(text = { Text(text = option) }, onClick = {
                                        selectedOptions[index] = option
                                        expandedIndex = -1
                                    })
                                }
                            }
                        }
                    }
                }
                HorizontalDivider(thickness = 1.dp, color = Color.Gray)
            }
        }
    }
}
