package com.gaby.kingoteka.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.gaby.kingoteka.R

sealed class ItemsMenu(
    val title: String,
    val route: String
) {
    companion object {
        @Composable
        fun getItems(): List<ItemsMenu> {
            return listOf(
                Search,
                User,
                Movies,
                Books,
                SV
            )
        }
    }

    @Composable
    abstract fun getIcon(): ImageVector

    object Search : ItemsMenu(title = "Buscar", route = AppScreen.Search.route) {
        @Composable
        override fun getIcon(): ImageVector = Icons.Outlined.Search
    }

    object User : ItemsMenu(title = "Usuario", route = AppScreen.User.route) {
        @Composable
        override fun getIcon(): ImageVector = Icons.Outlined.Person
    }

    object Movies : ItemsMenu(title = "Peliculas", route = AppScreen.Movies.route) {
        @Composable
        override fun getIcon(): ImageVector = ImageVector.vectorResource(id = R.drawable.ic_movie)
    }

    object Books : ItemsMenu(title = "Libros", route = AppScreen.Books.route) {
        @Composable
        override fun getIcon(): ImageVector = ImageVector.vectorResource(id = R.drawable.ic_book)
    }

    object SV : ItemsMenu(title = "SV", route = AppScreen.SV.route) {
        @Composable
        override fun getIcon(): ImageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_down)
    }
}