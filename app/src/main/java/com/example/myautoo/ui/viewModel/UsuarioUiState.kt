package com.example.myautoo.ui.viewModel

import com.example.myautoo.data.model.Usuario

data class UsuarioUiState(
    val nombre: String = "",
    val apellido: String = "",
    val rut: String = "",
    val correo: String = "",
    val contrasena: String = "",
    val aceptaTerminos: Boolean = false,
    val errores: UsuarioErorres = UsuarioErorres(),
    // Nuevas propiedades para autenticación
    val usuarioLogueado: Usuario? = null,
    val isLoading: Boolean = false,
    val loginError: String? = null
)