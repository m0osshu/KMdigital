package com.example.myautoo.ui.feature.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myautoo.R
import com.example.myautoo.navigation.Screens
import com.example.myautoo.ui.components.TopBar
import com.example.myautoo.ui.viewModel.AuthViewModel

@Composable
fun ProfileScreen(navController: NavController, authViewModel: AuthViewModel) {
    val user by authViewModel.user.collectAsState()

    Scaffold(
        topBar = {
            TopBar(
                title = "Perfil",
                backIconRes = R.drawable.back2,
                onBack = { navController.popBackStack() }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (user != null) {
                Text(text = "Has iniciado sesión como:", fontSize = 18.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = user?.email ?: "Email no disponible", fontSize = 20.sp)
                Spacer(modifier = Modifier.height(32.dp))
                Button(onClick = {
                    authViewModel.signOut()
                    // Navigate to login and clear back stack
                    navController.navigate(Screens.LOGIN) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }) {
                    Text(text = "Cerrar Sesión")
                }
            } else {
                Text(text = "No has iniciado sesión.", fontSize = 18.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    navController.navigate(Screens.LOGIN)
                }) {
                    Text(text = "Iniciar Sesión / Registrarse")
                }
            }
        }
    }
}