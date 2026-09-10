package cl.goodlike.service

import cl.goodlike.model.ReservaAlojamiento
import cl.goodlike.model.TipoAlojamiento
import cl.goodlike.registro.ResultadoRegistro

class MantenedorService {

    private val listaReservas = mutableListOf<ReservaAlojamiento>()

    fun registrarReserva(
        id: Int,
        nombreCliente: String,
        cantidadNoches: Int,
        valorNoche: Int,
        cantidadPersonas: Int,
        tipoAlojamiento: TipoAlojamiento
    ): ResultadoRegistro {

        if (id <= 0) {
            return ResultadoRegistro.Error("El identificador debe ser mayor que cero")
        }

        if (nombreCliente.isBlank()) {
            return ResultadoRegistro.Error("El nombre del cliente no puede estar vacio")
        }

        if (cantidadNoches <= 0) {
            return ResultadoRegistro.Error("La cantidad de noches debe ser mayor que cero")
        }

        if (valorNoche <= 0) {
            return ResultadoRegistro.Error("El valor por noche debe ser mayor que cero")
        }

        if (cantidadPersonas <= 0) {
            return ResultadoRegistro.Error("La cantidad de personas debe ser mayor que cero")
        }

        if (listaReservas.any { it.id == id }) {
            return ResultadoRegistro.Error("Ya existe una reserva con el identificador " + id)
        }

        val reserva = ReservaAlojamiento(valorNoche, cantidadPersonas, tipoAlojamiento)
        reserva.id = id
        reserva.nombreCliente = nombreCliente
        reserva.cantidadNoches = cantidadNoches

        listaReservas.add(reserva)

        return ResultadoRegistro.Exito(reserva)
    }

    fun obtenerReservas(): List<ReservaAlojamiento> {
        return listaReservas.toList()
    }
}
