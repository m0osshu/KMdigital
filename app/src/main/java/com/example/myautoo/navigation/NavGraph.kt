package com.example.myautoo.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myautoo.ui.feature.home.MainScreen
import com.example.myautoo.ui.viewModel.CarViewModel
import com.example.myautoo.ui.viewModel.CategoryViewModel

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    val categoryViewModel: CategoryViewModel = viewModel()
    val carViewModel: CarViewModel = viewModel()

    NavHost(navController = navController, startDestination = Screens.MAIN) {
        composable(Screens.MAIN) {
            MainScreen(
                onProfileClick = { navController.navigate(Screens.PROFILE) },
                onCarClick = { navController.navigate(Screens.DETAIL) },
                carViewModel = carViewModel,
                categoryViewModel = categoryViewModel
            )
        }
        composable(Screens.PROFILE) {
            // ProfileScreen(onBackClick = { navController.popBackStack() })
        }
        composable(Screens.DETAIL) {
            // DetailScreen()
        }
    }
}

object Screens {
    const val MAIN = "main"
    const val PROFILE = "profile"
    const val DETAIL = "detail"
}