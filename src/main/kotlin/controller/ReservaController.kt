package cl.goodlike.controller

import cl.goodlike.model.Reserva
import cl.goodlike.model.TipoAlojamiento
import cl.goodlike.registro.ResultadoRegistro
import cl.goodlike.service.MantenedorService

class ReservaController {

    private val mantenedorService = MantenedorService()

    fun ejecutar() {
        registrarReservas()
        listarReservas()
        mostrarConsultas()
        demostrarHerencia()
    }

    private fun registrarReservas() {
        println("=== REGISTRO DE RESERVAS ===")

        mostrarResultado(
            mantenedorService.registrarReserva(
                1, "Ana Perez", 3, 45000, 2, TipoAlojamiento.Habitacion)
        )
        mostrarResultado(
            mantenedorService.registrarReserva(2, "Luis Rojas", 5, 60000, 4, TipoAlojamiento.Cabana)
        )
        mostrarResultado(
            mantenedorService.registrarReserva(3, "Maria Soto", 2, 80000, 6, TipoAlojamiento.Departamento)
        )

        mostrarResultado(
            mantenedorService.registrarReserva(4, "", 2, 30000, 1, TipoAlojamiento.Habitacion)
        )
        mostrarResultado(
            mantenedorService.registrarReserva(1, "Pedro Diaz", 4, 20000, 2, TipoAlojamiento.Cabana)
        )

        registrarReservaConNochesEnTexto(5, "Carla Munoz", "tres", 25000, 2, TipoAlojamiento.Habitacion)

        println("------------------")
    }

    private fun registrarReservaConNochesEnTexto(
        id: Int,
        nombreCliente: String,
        cantidadNoches: String,
        valorNoche: Int,
        cantidadPersonas: Int,
        tipoAlojamiento: TipoAlojamiento
    ) {
        try {
            val noches = cantidadNoches.toInt()
            mostrarResultado(
                mantenedorService.registrarReserva(id, nombreCliente, noches, valorNoche, cantidadPersonas, tipoAlojamiento)
            )
        } catch (e: NumberFormatException) {
            println("ERROR CONTROLADO -> \"" + cantidadNoches + "\" no es un numero de noches valido, el programa continua")
        }
    }

    private fun mostrarResultado(resultado: ResultadoRegistro) {
        when (resultado) {
            is ResultadoRegistro.Exito -> println("EXITO -> reserva " + resultado.reserva.id + " de " + resultado.reserva.nombreCliente + " registrada")
            is ResultadoRegistro.Error -> println("ERROR -> " + resultado.mensaje)
        }
    }

    private fun listarReservas() {
        println("=== RESERVAS REGISTRADAS ===")

        val reservas = mantenedorService.obtenerReservas()

        for (reserva in reservas) {
            println("Id: " + reserva.id)
            println("Cliente: " + reserva.nombreCliente)
            println("Noches: " + reserva.cantidadNoches)
            println("Valor por noche: " + reserva.valorNoche)
            println("Personas: " + reserva.cantidadPersonas)
            println("Tipo de alojamiento: " + reserva.tipoAlojamiento + " - " + reserva.tipoAlojamiento.descripcion)
            println("Total reserva: " + reserva.valorTotal())
            println("Descripcion: " + reserva.descripcion())
            println("------------------")
        }
    }

    private fun mostrarConsultas() {
        println("=== CONSULTAS SOBRE LA COLECCION ===")

        val reservas = mantenedorService.obtenerReservas()

        val totalRecaudado = reservas.sumOf { it.valorTotal() }
        println("Total recaudado por todas las reservas: " + totalRecaudado)

        val reservasCaras = reservas.filter { it.valorTotal() > 150000 }
        println("Reservas sobre 150000: " + reservasCaras.map { it.nombreCliente })

        val reservasCabana = reservas.filter { it.tipoAlojamiento == TipoAlojamiento.Cabana }
        println("Reservas de tipo Cabana: " + reservasCabana.size)

        val nombresClientes = reservas.map { it.nombreCliente }
        println("Clientes con reserva: " + nombresClientes)

        println("------------------")
    }

    private fun demostrarHerencia() {
        println("=== HERENCIA Y POLIMORFISMO ===")

        val reservas = mantenedorService.obtenerReservas()

        if (reservas.isEmpty()) {
            println("No hay reservas para demostrar el comportamiento")
            return
        }

        val reservaGeneral: Reserva = reservas[0]

        println("Tratada como Reserva -> " + reservaGeneral.descripcion())
        println("Aunque la variable es de tipo Reserva, se ejecuta la descripcion de ReservaAlojamiento")

        println("------------------")
    }
}
