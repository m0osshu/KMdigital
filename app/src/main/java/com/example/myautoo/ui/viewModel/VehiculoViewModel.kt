package com.example.myautoo.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myautoo.data.model.Vehiculo
import com.example.myautoo.data.repository.VehiculoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class VehiculoViewModel (
    private val repo: VehiculoRepository = VehiculoRepository()
): ViewModel() {

    private val _items = MutableStateFlow<List<Vehiculo>>(emptyList())

    val items: StateFlow<List<Vehiculo>> = _items.asStateFlow()

    init{
        viewModelScope.launch{
            _items.value = repo.getAll()
        }
    }

    fun getItem(id: Int): Vehiculo? = repo.getById(id)
}