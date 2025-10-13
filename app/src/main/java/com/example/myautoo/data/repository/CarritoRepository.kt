package com.example.myautoo.data.repository

import com.example.myautoo.data.model.Vehiculo


class CarritoRepository {
    private val carrito = mutableListOf<Pair<Int, Vehiculo>>() // clienteId + vehiculo

    fun agregarAlCarrito(clienteId: Int, vehiculo: Vehiculo) {
        carrito.add(clienteId to vehiculo)
    }

    fun obtenerCarritoPorCliente(clienteId: Int): List<Vehiculo> {
        return carrito.filter { it.first == clienteId }.map { it.second }
    }

    fun eliminarDelCarrito(clienteId: Int, vehiculoId: Int) {
        carrito.removeIf { it.first == clienteId && it.second.id == vehiculoId }
    }

    fun vaciarCarrito(clienteId: Int) {
        carrito.removeIf { it.first == clienteId }
    }
}