package com.gaby.kingoteka.navigation

sealed class AppScreen(val route: String) {
    object User: AppScreen("user")
    object Movies: AppScreen("movies")
    object Books: AppScreen("books")
    object Search: AppScreen("search")
    object SV: AppScreen("sv")
    object BookDetails: AppScreen("bookDetail")
}