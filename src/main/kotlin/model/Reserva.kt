package cl.goodlike.model

open class Reserva(
    var id: Int = 0,
    var nombreCliente: String = "",
    var cantidadNoches: Int = 0
) {

    open fun descripcion(): String {
        return "Reserva general N " + id + " del cliente " + nombreCliente + " por " + cantidadNoches + " noche(s)"
    }
}
