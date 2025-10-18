package com.example.myautoo.ui.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myautoo.data.model.CarModel
import androidx.compose.runtime.State
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.launch

class CarViewModel: ViewModel() {
    private val _cars = mutableStateOf<List<CarModel>>(emptyList())
    val cars: State<List<CarModel>> = _cars

    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error

    init {
        viewModelScope.launch {
            fetchCars()
        }
    }

    private fun fetchCars() {
        try {
            val ref = FirebaseDatabase.getInstance().getReference("Cars")

            ref.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val temp = mutableListOf<CarModel>()
                    for (child in snapshot.children) {
                        try {
                            child.getValue(CarModel::class.java)?.let {
                                temp.add(it)
                            }
                        } catch (e: Exception) {
                            _error.value = "Error parsing car data: ${e.message}"
                        }
                    }
                    _cars.value = temp
                    _isLoading.value = false
                }

                override fun onCancelled(error: DatabaseError) {
                    _isLoading.value = false
                    _error.value = "Firebase error: ${error.message}"
                }
            })
        } catch (e: Exception) {
            _isLoading.value = false
            _error.value = "Initialization error: ${e.message}"
        }
    }
}