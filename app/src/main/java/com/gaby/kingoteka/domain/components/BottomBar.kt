package com.gaby.kingoteka.domain.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.gaby.kingoteka.navigation.ItemsMenu

@Composable
fun NavegacionInferior(navController: NavHostController) {

    val menu_items = listOf(

        ItemsMenu.Books,
        ItemsMenu.Movies,
        ItemsMenu.Search,
        ItemsMenu.SV,
        ItemsMenu.User


        )

    BottomAppBar(modifier = Modifier.fillMaxWidth(1f)){
        NavigationBar(containerColor = Color.White, modifier = Modifier.fillMaxWidth()){
            menu_items.forEach { item ->
                NavigationBarItem(selected = false,
                    onClick = { navController.navigate(item.route) },
                    icon = {
                        Icon(imageVector = item.icon, contentDescription = item.title, tint = Color.Black)
                    },
                    label = {
                        Text(text = item.title, color = Color.Black)
                    })
            }
        }
    }


}