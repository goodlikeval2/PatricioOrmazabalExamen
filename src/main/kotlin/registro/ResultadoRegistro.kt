package cl.goodlike.registro

import cl.goodlike.model.ReservaAlojamiento

sealed class ResultadoRegistro {

    data class Exito(
        val reserva: ReservaAlojamiento
    ) : ResultadoRegistro()

    data class Error(
        val mensaje: String = "No fue posible registrar la reserva"
    ) : ResultadoRegistro()
}
