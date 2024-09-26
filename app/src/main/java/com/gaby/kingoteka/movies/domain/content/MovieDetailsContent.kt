package com.gaby.kingoteka.movies.domain.content

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.gaby.kingoteka.general_components.CardMovie
import com.gaby.kingoteka.general_components.SharedViewModel
import com.gaby.kingoteka.movies.domain.models.MovieModelDetail

@Composable
fun MovieDetailsContent(
    movieSelected: MovieModelDetail?,
    sharedViewModel: SharedViewModel,
    navHostController: NavHostController
) {
    if (movieSelected == null) {

        return
    }

    val selectedOption by sharedViewModel.selectedOptions.collectAsState()
    val rating by sharedViewModel.ratings.collectAsState()

    val currentSelectedOption = selectedOption[movieSelected.id.toString()] ?: "Pendiente"
    val currentRating = rating[movieSelected.id.toString()] ?: 1f

    Column(
        modifier = Modifier.padding(16.dp).fillMaxWidth().verticalScroll(rememberScrollState())
            .padding(bottom = 16.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CardMovie(
            movie = movieSelected, navController = navHostController,onClick = {}
        )

        movieSelected.title?.let {
            Text(
                text = it,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 8.dp).align(Alignment.CenterHorizontally)
            )
        }

        Spacer(modifier = Modifier.height(3.dp))

        movieSelected.director?.let {
            Text(
                text = it,
                color = Color.Black,
                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Año de publicación:" + movieSelected.year.toString(),
            color = Color.Black,
            fontSize = 15.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = "Tipo:" + movieSelected.tipo,
            color = Color.Black,
            fontSize = 15.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        DropdownMenuExample(
            bookId = movieSelected.id.toString(),
            sharedViewModel = sharedViewModel,
            selectedOption = currentSelectedOption
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Clasificalo:",
            color = Color.Black,
            fontSize = 15.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        StarRatingBar(
            bookId = movieSelected.id.toString(),
            initialRating = currentRating,
            sharedViewModel = sharedViewModel
        )

        Spacer(modifier = Modifier.height(10.dp))

        HorizontalDivider(thickness = 1.dp, color = Color.LightGray)

        Text(
            text = movieSelected.notes.toString(),
            color = Color.Black,
            fontSize = 15.sp,
            fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(20.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))


        Spacer(modifier = Modifier.height(50.dp)) // Añadir espacio adicional al final
    }
}

@Composable
fun DropdownMenuExample(bookId: String, sharedViewModel: SharedViewModel, selectedOption: String) {
    val options = listOf("Pendiente", "Leyendo", "Leído")
    var expanded by remember { mutableStateOf(false) }

    val backgroundColor = when (selectedOption) {
        "Pendiente" -> Color(0xFF7626E9)
        "Leyendo" -> Color(0xFF26E99F)
        "Leído" -> Color(0xFFFFA500)
        else -> Color.Gray
    }

    Box(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier.clickable { expanded = true }.background(backgroundColor)
                .padding(8.dp).width(150.dp)
        ) {
            Text(
                text = selectedOption, color = Color.White, modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Dropdown Arrow",
                tint = Color.White
            )
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { option ->
                DropdownMenuItem(text = { Text(text = option) }, onClick = {
                    sharedViewModel.updateSelectedOption(bookId, option)
                    expanded = false
                })
            }
        }
    }
}


@Composable
fun StarRatingBar(
    bookId: String, initialRating: Float, sharedViewModel: SharedViewModel, maxStars: Int = 5
) {
    var rating by remember { mutableStateOf(initialRating) }
    val density = LocalDensity.current.density
    val starSize = (12f * density).dp
    val starSpacing = (0.5f * density).dp

    Row(
        modifier = Modifier.selectableGroup(), verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 1..maxStars) {
            val isSelected = i <= rating
            val icon = if (isSelected) Icons.Filled.Star else Icons.Outlined.Star
            val iconTintColor = if (isSelected) Color(0xFFFFC700) else Color.Black

            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconTintColor,
                modifier = Modifier.selectable(selected = isSelected, onClick = {
                    rating = i.toFloat()
                    sharedViewModel.updateRating(bookId, rating)
                }).width(starSize).height(starSize)
            )

            if (i < maxStars) {
                Spacer(modifier = Modifier.width(starSpacing))
            }
        }
    }
}
