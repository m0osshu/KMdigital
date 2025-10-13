package com.example.myautoo.data.model

open class Usuario(
    val id: Int,
    val nombre: String,
    val apellido: String,
    val rut: String,
    val correo: String,
    val contrasena: String
){
    open fun mostrarDatos(): String {
        return "ID: $id | Nombre: $nombre $apellido | Correo: $correo"
    }
}