package com.example.myautoo.ui.feature

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myautoo.CatalogoContent
import com.example.myautoo.R
import com.example.myautoo.navigation.Routes
import com.example.myautoo.ui.viewModel.UsuarioViewModel
import com.example.myautoo.ui.viewModel.VehiculoViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    vehiculoViewModel: VehiculoViewModel,
    usuarioViewModel: UsuarioViewModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        // Header fijo
        HeaderSection(username = "pirulin", onBellClick = {})
        SearchSection()

        // Contenido principal - Catálogo integrado
        CatalogoContent(
            viewModel = vehiculoViewModel,
            onItemClick = { id ->
                navController.navigate("detalle/$id")
            },
            modifier = Modifier.weight(1f)
        )
    }
}

// ------------------- HEADER Y SEARCH ---------------------

@Composable
fun HeaderSection(username: String, onBellClick: () -> Unit) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(16.dp)
    ) {
        val (profilePic, nameColumn, bellIcon) = createRefs()

        Image(
            painter = painterResource(R.drawable.profile),
            contentDescription = "Profile picture",
            modifier = Modifier
                .size(55.dp)
                .constrainAs(profilePic) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )

        Column(
            modifier = Modifier.constrainAs(nameColumn) {
                start.linkTo(profilePic.end, margin = 8.dp)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
        ) {
            Text(text = "Bienvenido", fontSize = 14.sp, color = Color.Gray)
            Text(
                text = username,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        IconButton(
            onClick = onBellClick,
            modifier = Modifier.constrainAs(bellIcon) {
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
        ) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Notificaciones",
                tint = Color.Black
            )
        }
    }
}

@Composable
fun SearchSection() {
    ConstraintLayout(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .height(50.dp)
            .fillMaxWidth()
    ) {
        val (searchInput, iconBox) = createRefs()

        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = { Text(text = "Buscar auto...") },
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = "Buscar")
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(25.dp),
            modifier = Modifier
                .constrainAs(searchInput) {
                    start.linkTo(parent.start)
                    end.linkTo(iconBox.start, margin = 8.dp)
                    width = Dimension.fillToConstraints
                }
        )

        Box(
            modifier = Modifier
                .size(48.dp)
                .background(Color.Black, CircleShape)
                .constrainAs(iconBox) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Configuración",
                tint = Color.White
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        navController = rememberNavController(),
        vehiculoViewModel = VehiculoViewModel(),
        usuarioViewModel = UsuarioViewModel()
    )
}
