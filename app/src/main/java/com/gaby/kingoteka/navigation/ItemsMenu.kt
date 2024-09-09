package com.gaby.kingoteka.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ItemsMenu(

    val icon:ImageVector,
    val title:String,
    val route:String
) {

    object Search: ItemsMenu(Icons.Outlined.Search, title = "Buscar", route = AppScreen.Search.route)
    object User: ItemsMenu(Icons.Outlined.Person, title = "Usuario", route = AppScreen.User.route)
    object Movies: ItemsMenu(Icons.Outlined.Search, title = "Peliculas", route = AppScreen.Movies.route)
    object Books: ItemsMenu(Icons.Outlined.Search, title = "Libros", route = AppScreen.Books.route)
    object SV: ItemsMenu(Icons.Outlined.Search, title = "SV", route = AppScreen.SV.route)
    

}