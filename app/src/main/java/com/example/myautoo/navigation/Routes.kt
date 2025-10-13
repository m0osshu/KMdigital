// Routes.kt o donde tengas tu configuración de navegación
package com.example.myautoo.navigation

object Routes {
    // Auth Flow
    const val INTRO = "intro"
    const val REGISTRO = "registro"
    const val LOGIN = "login"

    // Main App Flow
    const val HOME = "inicio"
    const val CARRITO = "carrito"
    const val PERFIL = "perfil"
    const val DETALLE = "detalle/{id}"

    fun detalleRoute(id: Int) = "detalle/$id"
}

