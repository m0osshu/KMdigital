package com.example.myautoo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myautoo.data.repository.CarritoRepository
import com.example.myautoo.navigation.BottomBar
import com.example.myautoo.navigation.BottomNavItem
import com.example.myautoo.navigation.Routes
import com.example.myautoo.ui.feature.*
import com.example.myautoo.ui.theme.MyAutooTheme
import com.example.myautoo.ui.viewModel.CarritoViewModel
import com.example.myautoo.ui.viewModel.UsuarioViewModel
import com.example.myautoo.ui.viewModel.VehiculoViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyAutooTheme {
                App()
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    val usuarioViewModel: UsuarioViewModel = viewModel()
    val vehiculoViewModel: VehiculoViewModel = viewModel()

    val usuarioState by usuarioViewModel.estado.collectAsState()
    val clienteId = usuarioState.usuarioLogueado?.id ?: 0

    // Crea el ViewModel manualmente
    val carritoRepository = CarritoRepository()
    val carritoViewModel = remember { CarritoViewModel(carritoRepository) }

    val bottomItems = listOf(
        BottomNavItem.Home,
        BottomNavItem.Carrito,
        BottomNavItem.Perfil
    )

    // NAVEGACIÓN PRINCIPAL - UN SOLO NavHost
    NavHost(
        navController = navController,
        startDestination = Routes.INTRO
    ) {
        // PANTALLAS DE AUTH (SIN BOTTOM BAR)
        composable(Routes.INTRO) {
            IntroScreen(
                navToMain = {
                    navController.navigate(Routes.LOGIN)
                }
            )
        }

        composable(Routes.LOGIN) {
            LoginScreen(
                navController = navController,
                viewModel = usuarioViewModel
            )
        }

        composable(Routes.REGISTRO) {
            RegistroScreen(
                navController = navController,
                viewModel = usuarioViewModel
            )
        }

        // PANTALLA HOME (CON BOTTOM BAR) - Muestra Header + Catálogo/Perfil
        composable(Routes.HOME) {
            Scaffold(
                bottomBar = {
                    BottomBar(navController = navController, items = bottomItems)
                }
            ) { innerPadding ->
                HomeContent(
                    navController = navController,
                    modifier = Modifier.padding(innerPadding),
                    vehiculoViewModel = vehiculoViewModel,
                    usuarioViewModel = usuarioViewModel
                )
            }
        }

        // PANTALLA CATÁLOGO INDEPENDIENTE (CON BOTTOM BAR)
        composable(Routes.CARRITO) {
            Scaffold(
                bottomBar = {
                    BottomBar(navController = navController, items = bottomItems)
                }
            ) { innerPadding ->
                CarritoScreen(
                    viewModel = carritoViewModel,
                    clienteId = id,
                    onBack = { navController.popBackStack() }
                )
            }
        }

        // PANTALLA PERFIL INDEPENDIENTE (CON BOTTOM BAR)
        composable(Routes.PERFIL) {
            Scaffold(
                bottomBar = {
                    BottomBar(navController = navController, items = bottomItems)
                }
            ) { innerPadding ->
                PerfilScreen(
                    viewModel = usuarioViewModel,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }

        // PANTALLAS DE DETALLE (SIN BOTTOM BAR)
        composable(
            route = Routes.DETALLE,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: -1
            DetalleScreen(
                vehiculoId = id,
                viewModel = vehiculoViewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}

// COMPONENTE HOME CONTENT (sin NavHost interno)
@Composable
fun HomeContent(
    navController: androidx.navigation.NavHostController,
    modifier: Modifier = Modifier,
    vehiculoViewModel: VehiculoViewModel,
    usuarioViewModel: UsuarioViewModel
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        // Header fijo
        HeaderSection(username = "Daniel Nuñez", onBellClick = {})
        SearchSection()

        // Contenido principal - Catálogo integrado (sin navegación interna)
        CatalogoContent(
            viewModel = vehiculoViewModel,
            onItemClick = { id ->
                navController.navigate(Routes.detalleRoute(id))
            },
            modifier = Modifier.weight(1f)
        )
    }
}

// COMPONENTE CATALOGO CONTENT (reutilizable)
@Composable
fun CatalogoContent(
    viewModel: VehiculoViewModel,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val vehiculos = viewModel.items.collectAsState()

    Column(modifier = modifier) {
        LazyColumn(contentPadding = PaddingValues(8.dp)) {
            items(vehiculos.value) { vehiculo ->
                VehiculoRow(
                    vehiculo = vehiculo,
                    onClick = { onItemClick(vehiculo.id) }
                )
                HorizontalDivider()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    MyAutooTheme {
        App()
    }
}