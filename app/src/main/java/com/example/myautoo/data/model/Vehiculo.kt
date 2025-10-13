package com.example.myautoo.data.model

data class Vehiculo(
    val id: Int,
    val anio: Int,
    val kilometraje: Int,
    val precio: Int,
    val combustible: String,
    val color: String,
    val marca: String,
    val modelo: String,
    val descripcion: String,
    var cantidad: Int,
    val categoria: String,
    val imagen: Int
) {
    fun mostrarDatos(): String {
        return """
            ID: $id
            Modelo: $modelo
            Marca: $marca
            Año: $anio
            Precio: $$precio
            Kilometraje: $kilometraje km
            Combustible: $combustible
            Color: $color
            Categoría: ${categoria}
            Descripción: $descripcion
            Cantidad: $cantidad
            Imagen: $imagen
        """.trimIndent()
    }
}