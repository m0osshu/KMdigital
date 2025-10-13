package com.example.myautoo.data.model

data class Cliente(
    val clienteId: Int,
    val clienteNombre: String,
    val clienteApellido: String,
    val clienteRut: String,
    val clienteCorreo: String,
    val clienteContrasena: String
) : Usuario(clienteId, clienteNombre, clienteApellido, clienteRut, clienteCorreo, clienteContrasena) {
    override fun mostrarDatos(): String {
        return "Cliente → ${super.mostrarDatos()}"
    }
}