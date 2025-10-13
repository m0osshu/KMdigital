package com.example.myautoo.data.repository

import com.example.myautoo.data.model.Admin
import com.example.myautoo.data.model.Cliente
import com.example.myautoo.data.model.Usuario

class UsuarioRepository {
    val cliente = Cliente(1, "Daniel", "Nuñez", "11.111.111-1", "dani@mail.com", "1234")
    val admin = Admin(2, "Lucas", "Huerta", "22.222.222-2", "lucas@mail.com", "abcd")

    fun autenticar(correo: String, contrasena: String): Usuario? {
        return when {
            correo == cliente.clienteCorreo && contrasena == cliente.clienteContrasena -> cliente
            correo == admin.adminCorreo && contrasena == admin.adminContrasena -> admin
            else -> null
        }
    }
}