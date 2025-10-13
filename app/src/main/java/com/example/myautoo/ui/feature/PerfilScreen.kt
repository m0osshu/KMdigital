package com.example.myautoo.ui.feature

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myautoo.ui.viewModel.UsuarioViewModel

@Composable
fun PerfilScreen(
    viewModel: UsuarioViewModel,
    modifier: Modifier = Modifier
) {
    val estado by viewModel.estado.collectAsState()
    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        HeaderSection(username = "Daniel Nuñez", onBellClick = {})
        SearchSection()
        Text("Perfil del usuario", style = MaterialTheme.typography.titleLarge)
        Text("Nombre: ${estado.nombre} ${estado.apellido}")
        Text("Correo: ${estado.correo}")
        Text("Rut: ${estado.rut}")

        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = {
                // Aquí iría la lógica para logout
                // Por ahora navegamos al login
                // navController.navigate(Routes.LOGIN)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cerrar Sesión")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun perfilScreenPreview() {
    PerfilScreen(viewModel = UsuarioViewModel())
}