package com.gaby.kingoteka.books.domain.content


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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.gaby.kingoteka.R
import com.gaby.kingoteka.books.domain.models.BookModelDetail
import com.gaby.kingoteka.general_components.CardBook
import com.gaby.kingoteka.general_components.SharedViewModel


@Composable
fun BookDetailsContent(
    bookSelected: BookModelDetail?,
    sharedViewModel: SharedViewModel,
    navHostController: NavHostController
) {
    if (bookSelected == null) {

        return
    }

    val bookStatuses by sharedViewModel.bookStatuses.collectAsState()
    val ratings by sharedViewModel.ratings.collectAsState()

    val currentBookStatus = bookStatuses[bookSelected.id.toString()] ?: stringResource(R.string.book_status_pending)
    val currentRating = ratings[bookSelected.id.toString()] ?: 0f

    Column(
        modifier = Modifier.padding(16.dp).fillMaxWidth().verticalScroll(rememberScrollState())
            .padding(bottom = 16.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CardBook(
            book = bookSelected, navController = navHostController, onClick = {}
        )

        Text(
            text = bookSelected.titulo,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 20.sp,
            modifier = Modifier.padding(start = 8.dp).align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(3.dp))

        Text(
            text = bookSelected.titulo_original,
            color = Color.Black,
            fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(10.dp))

        HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Año de publicación:" + bookSelected.anio.toString(),
            color = Color.Black,
            fontSize = 15.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = "Paginas:" + bookSelected.paginas.toString(),
            color = Color.Black,
            fontSize = 15.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Text(
            text = "Género:" + bookSelected.genero.toString(),
            color = Color.Black,
            fontSize = 15.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        BookStatusSelector(
            bookId = bookSelected.id.toString(),
            sharedViewModel = sharedViewModel,
            selectedOption = currentBookStatus
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Clasificalo:",
            color = Color.Black,
            fontSize = 15.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        StarRatingBar(
            bookId = bookSelected.id.toString(),
            initialRating = currentRating,
            sharedViewModel = sharedViewModel
        )

        Spacer(modifier = Modifier.height(10.dp))

        HorizontalDivider(thickness = 1.dp, color = Color.LightGray)

        Text(
            text = bookSelected.notas.toString(),
            color = Color.Black,
            fontSize = 15.sp,
            fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(20.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Sinopsis:",
            color = Color.Black,
            fontSize = 15.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(20.dp)
        )

        Spacer(modifier = Modifier.height(3.dp))

        Text(
            text = bookSelected.sinopsis,
            color = Color.Black,
            fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
            fontSize = 15.sp,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(50.dp)) // Añadir espacio adicional al final
    }
}

@Composable
fun BookStatusSelector(bookId: String, sharedViewModel: SharedViewModel, selectedOption: String) {
    val options = listOf(
        stringResource(R.string.book_status_pending),
        stringResource(R.string.book_status_reading),
        stringResource(R.string.book_status_read)
    )
    var expanded by remember { mutableStateOf(false) }

    val backgroundColor = when (selectedOption) {
        stringResource(R.string.book_status_pending) -> Color(0xFF7626E9)
        stringResource(R.string.book_status_reading) -> Color(0xFF26E99F)
        stringResource(R.string.book_status_read) -> Color(0xFFFFA500)
        else -> Color.Gray
    }

    Box(modifier = Modifier.padding(16.dp)) {
        Row(modifier = Modifier.clickable { expanded = true }.background(backgroundColor)
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
                    sharedViewModel.updateBookStatus(bookId, option)
                    expanded = false
                })
            }
        }
    }
}


@Composable
fun StarRatingBar(
    bookId: String,
    initialRating: Float,
    sharedViewModel: SharedViewModel,
    maxStars: Int = 5,
    readOnly: Boolean = false
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

            Icon(imageVector = icon,
                contentDescription = null,
                tint = iconTintColor,
                modifier = if (readOnly) Modifier else Modifier.selectable(
                    selected = isSelected,
                    onClick = {
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