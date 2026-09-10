package cl.goodlike.service

import kotlinx.coroutines.delay

suspend fun consultarDisponibilidad(): String {
    println("Consultando disponibilidad de alojamientos...")
    delay(1000)
    return "Disponibilidad confirmada, se puede continuar con las reservas"
}
