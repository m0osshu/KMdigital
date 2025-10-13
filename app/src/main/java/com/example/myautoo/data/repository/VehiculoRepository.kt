package com.example.myautoo.data.repository

import com.example.myautoo.R
import com.example.myautoo.data.model.Vehiculo


class VehiculoRepository {

    private val vehiculos = listOf(
        Vehiculo(
            1, 2023, 5000, 45000, "Eléctrico", "Rojo",
            "Tesla", "Model 3", "Sedán eléctrico de alto rendimiento", 4,
            "Automovil", R.drawable.car
        ),
        Vehiculo(
            2, 2022, 15000, 25000, "Gasolina", "Azul",
            "Toyota", "GR86", "Deportivo compacto", 5,
            "Automovil", R.drawable.car1
        ),
        Vehiculo(
            3, 2023, 8000, 50000, "Gasolina", "Blanco",
            "Porsche", "911", "Deportivo de lujo", 5,
            "Automovil", R.drawable.car2
        ),
        Vehiculo(
            4, 2022, 20000, 23000, "Gasolina", "Verde",
            "Jeep", "Wrangler", "SUV todoterreno", 5,
            "SUV", R.drawable.car3
        ),
        Vehiculo(
            5, 2023, 10000, 18500, "Gasolina", "Naranja",
            "Ford", "Bronco", "SUV robusto", 6,
            "SUV", R.drawable.car4
        ),
        Vehiculo(
            6, 2023, 3000, 20000, "Gasolina", "Amarillo",
            "Lamborghini", "Miura", "Deportivo clásico", 4,
            "Automovil", R.drawable.car5
        )
    )

    fun getAll(): List<Vehiculo> = vehiculos
    fun getById(id: Int): Vehiculo? = vehiculos.find { it.id == id}
}