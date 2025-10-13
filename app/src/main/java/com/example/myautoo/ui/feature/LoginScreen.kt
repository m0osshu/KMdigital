package com.example.myautoo.ui.feature

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myautoo.data.repository.UsuarioRepository
import com.example.myautoo.navigation.Routes
import com.example.myautoo.ui.viewModel.UsuarioViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: UsuarioViewModel
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    val usuarioRepository = UsuarioRepository()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título
        Text(
            text = "Iniciar Sesión",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Bienvenido de vuelta",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Campo de correo
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                errorMessage = "" // Limpiar error al escribir
            },
            label = { Text("Correo electrónico") },
            leadingIcon = {
                Icon(Icons.Default.Email, contentDescription = "Email")
            },
            isError = errorMessage.isNotEmpty(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de contraseña
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                errorMessage = "" // Limpiar error al escribir
            },
            label = { Text("Contraseña") },
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = "Contraseña")
            },
            visualTransformation = PasswordVisualTransformation(),
            isError = errorMessage.isNotEmpty(),
            modifier = Modifier.fillMaxWidth()
        )

        // Mensaje de error
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Botón de inicio de sesión
        Button(
            onClick = {
                when {
                    email.isBlank() && password.isBlank() -> {
                        errorMessage = "Por favor ingresa tu correo y contraseña"
                    }
                    email.isBlank() -> {
                        errorMessage = "Por favor ingresa tu correo electrónico"
                    }
                    password.isBlank() -> {
                        errorMessage = "Por favor ingresa tu contraseña"
                    }
                    !email.contains("@") -> {
                        errorMessage = "Por favor ingresa un correo válido"
                    }
                    else -> {
                        //AUTENTICACIÓN con Repository
                        val usuarioAutenticado = autenticarConRepository(
                            email = email,
                            password = password,
                            repository = usuarioRepository
                        )

                        if (usuarioAutenticado != null) {

                            navController.navigate(Routes.HOME) {
                                // Limpiar el back stack completo
                                popUpTo(Routes.INTRO) { inclusive = true }
                            }
                        } else {
                            errorMessage = "Correo o contraseña incorrectos"
                        }
                    }
                }
            },
            enabled = !isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            } else {
                Text(
                    text = "Iniciar Sesión",
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Usuarios de prueba:",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Botón para autocompletar cliente
            OutlinedButton(
                onClick = {
                    email = "dani@mail.com"
                    password = "1234"
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cliente: dani@mail.com / 1234")
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Botón para autocompletar admin
            OutlinedButton(
                onClick = {
                    email = "lucas@mail.com"
                    password = "abcd"
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Admin: lucas@mail.com / abcd")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Opción para registrarse
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "¿No tienes cuenta? ",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            TextButton(
                onClick = {
                    navController.navigate(Routes.REGISTRO)
                },
                modifier = Modifier.padding(start = 4.dp)
            ) {
                Text("Regístrate")
            }
        }
    }
}

private fun autenticarConRepository(
    email: String,
    password: String,
    repository: UsuarioRepository
): Any? { // Retorna Any? porque podrías tener Cliente o Admin

    return when {
        //Verificar cliente
        email == repository.cliente.clienteCorreo &&
                password == repository.cliente.clienteContrasena -> repository.cliente

        // Verificar admin
        email == repository.admin.adminCorreo &&
                password == repository.admin.adminContrasena -> repository.admin

        else -> null
    }
}

@Preview(showBackground = true)
@Composable
fun loginScreenPreview() {

    val navController = NavController(LocalContext.current)
    LoginScreen(navController = navController, viewModel = viewModel())
}