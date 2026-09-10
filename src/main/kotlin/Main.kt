package cl.goodlike

import cl.goodlike.controller.ReservaController
import cl.goodlike.service.consultarDisponibilidad

import kotlinx.coroutines.runBlocking

fun main() {

    val reservaController = ReservaController()

    runBlocking {
        val disponibilidad = consultarDisponibilidad()
        println(disponibilidad)
    }

    println("------------------")

    reservaController.ejecutar()
}
