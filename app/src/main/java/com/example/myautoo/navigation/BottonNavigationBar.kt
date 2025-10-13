package com.example.myautoo.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.R
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState



import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

sealed class BottomNavItem(
    val route: String,
    val label: String,
    @DrawableRes val iconRes: Int
) {
    object Home : BottomNavItem(Routes.HOME, "Inicio", com.example.myautoo.R.drawable.btn_1)
    object Carrito : BottomNavItem(Routes.CARRITO, "Carrito", com.example.myautoo.R.drawable.btn_3)
    object Perfil : BottomNavItem(Routes.PERFIL, "Perfil", com.example.myautoo.R.drawable.btn_4)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBar(navController: NavHostController, items: List<BottomNavItem>) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        containerColor = Color.Black  // Fondo negro para la barra
    ) {
        items.forEach { item ->
            val selected = currentRoute == item.route
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconRes),
                        contentDescription = item.label,
                        tint = if (selected) Color.White else Color.White.copy(alpha = 0.6f) // Blanco opaco si no está seleccionado
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        color = if (selected) Color.White else Color.White.copy(alpha = 0.6f) // Etiqueta blanca o semi-transparente
                    )
                },
                selected = selected,
                onClick = {
                    if (!selected) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationRoute ?: Routes.HOME) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}