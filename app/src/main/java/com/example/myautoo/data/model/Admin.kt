package com.example.myautoo.data.model

data class Admin(
    val adminId: Int,
    val adminNombre: String,
    val adminApellido: String,
    val adminRut: String,
    val adminCorreo: String,
    val adminContrasena: String
) : Usuario(adminId, adminNombre, adminApellido, adminRut, adminCorreo, adminContrasena) {
    override fun mostrarDatos(): String {
        return "Admin → ${super.mostrarDatos()}"
    }
}