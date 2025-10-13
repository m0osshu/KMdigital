package com.example.myautoo.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.myautoo.data.model.Vehiculo
import com.example.myautoo.data.repository.CarritoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CarritoViewModel(
    private val repository: CarritoRepository): ViewModel() {
    private val _carrito = MutableStateFlow<List<Vehiculo>>(emptyList())
    val carrito: StateFlow<List<Vehiculo>> = _carrito

    fun cargarCarrito(clienteId: Int) {
        _carrito.value = repository.obtenerCarritoPorCliente(clienteId)
    }

    fun agregar(clienteId: Int, vehiculo: Vehiculo) {
        repository.agregarAlCarrito(clienteId, vehiculo)
        cargarCarrito(clienteId)
    }

    fun eliminar(clienteId: Int, vehiculoId: Int) {
        repository.eliminarDelCarrito(clienteId, vehiculoId)
        cargarCarrito(clienteId)
    }

    fun vaciar(clienteId: Int) {
        repository.vaciarCarrito(clienteId)
        cargarCarrito(clienteId)
    }
}