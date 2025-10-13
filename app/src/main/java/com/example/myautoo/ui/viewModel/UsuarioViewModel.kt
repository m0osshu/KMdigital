package com.example.myautoo.ui.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class UsuarioViewModel: ViewModel() {


    //Estado interno, mutable porque cambia
    private val _estado = MutableStateFlow(UsuarioUiState())

    //Estado externo, lectura del estado en la UI
    val estado: StateFlow<UsuarioUiState> = _estado

    //Actualizar campo nombre y limpiar estado
    fun onNombreChange(valor: String){
        _estado.update { it.copy(nombre = valor, errores = it.errores.copy(nombre = null)) }
    }

    //Actualizar campo apellido y limpiar estado
    fun onApellidoChange(valor: String){
        _estado.update { it.copy(apellido = valor, errores = it.errores.copy(apellido = null)) }
    }

    //Actualizar campo rut y limpiar estado
    fun onRutChange(valor: String){
        _estado.update { it.copy(rut = valor, errores = it.errores.copy(rut = null)) }
    }

    //Actualizar campo correo y limpiar estado
    fun onCorreoChange(valor: String){
        _estado.update { it.copy(correo = valor, errores = it.errores.copy(correo = null)) }
    }

    //Actualizar campo contrasena y limpiar estado
    fun onContrasenaChange(valor: String){
        _estado.update { it.copy(contrasena = valor, errores = it.errores.copy(contrasena = null)) }
    }

    fun onAceptaTerminosChange(valor: Boolean){
        _estado.update { it.copy(aceptaTerminos = valor) }
    }

    //Validacion del formulario
    fun validarFormulario(): Boolean{
        val estadoActual = _estado.value
        val errores = UsuarioErorres(
            nombre = if (estadoActual.nombre.isBlank()) "El nombre es obligatorio" else null,
            apellido = if (estadoActual.apellido.isBlank()) "El apellido es obligatorio" else null,
            rut = if (estadoActual.rut.isBlank()) "El rut es obligatorio" else null,
            correo = if (estadoActual.correo.isBlank()) "El correo es obligatorio" else null,
            contrasena = if (estadoActual.contrasena.isBlank()) "La contraseña es obligatoria" else null
        )

        val hayErrores = listOfNotNull(
            errores.nombre,
            errores.apellido,
            errores.rut,
            errores.correo,
            errores.contrasena
        ).isNotEmpty()

        _estado.update { it.copy(errores = errores) }
        return !hayErrores
    }

}