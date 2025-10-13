package com.example.myautoo.ui.feature

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myautoo.CatalogoContent
import com.example.myautoo.data.model.Cliente
import com.example.myautoo.data.repository.CarritoRepository
import com.example.myautoo.ui.viewModel.CarritoViewModel
import com.example.myautoo.ui.viewModel.VehiculoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarritoScreen(
    viewModel: CarritoViewModel,
    clienteId: Int,
    onBack: () -> Unit
) {
    val carrito by viewModel.carrito.collectAsState()

    // Cargar carrito al iniciar
    LaunchedEffect(clienteId) {
        viewModel.cargarCarrito(clienteId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tu carrito") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            if (carrito.isEmpty()) {
                Text(
                    "Tu carrito está vacío",
                    modifier = Modifier.padding(16.dp)
                )
            } else {
                carrito.forEach { vehiculo ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Text("${vehiculo.marca} ${vehiculo.modelo}")
                        Text("$${vehiculo.precio}")

                        Button(
                            onClick = {
                                viewModel.eliminar(clienteId = clienteId, vehiculoId = vehiculo.id)
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Eliminar")
                        }
                    }
                }

                val total = carrito.sumOf { it.precio }
                Spacer(Modifier.height(16.dp))
                Text(
                    "Total: $$total",
                    style = MaterialTheme.typography.headlineSmall
                )

                Button(
                    onClick = {
                        viewModel.vaciar(clienteId)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Vaciar Carrito")
                }
            }
        }
    }
}

/**@Preview(showBackground = true)
@Composable
fun carritoScreenPreview() {
    CarritoScreen(
        viewModel = carritoViewModel,
        clienteId = cliente.id
    )
}**/