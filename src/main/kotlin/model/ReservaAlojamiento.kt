package cl.goodlike.model

data class ReservaAlojamiento(
    var valorNoche: Int = 0,
    var cantidadPersonas: Int = 0,
    var tipoAlojamiento: TipoAlojamiento = TipoAlojamiento.Habitacion
) : Reserva() {

    fun valorTotal(): Int {
        return valorNoche * cantidadNoches
    }

    override fun descripcion(): String {
        return "Alojamiento tipo " + tipoAlojamiento + " (" + tipoAlojamiento.descripcion + ") para " +
                cantidadPersonas + " persona(s), " + cantidadNoches + " noche(s), total " + valorTotal()
    }
}
