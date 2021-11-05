package tierraMedia;

public class Porcentuales extends Promociones {

	private double porcentaje;

	public Porcentuales(int id, String tipo, String nombre, String promociones, double porcentaje) throws PromocionesException {
		super(id, tipo, nombre, promociones);
		if (porcentaje <= 0) {
			throw new PromocionesException("El porcentaje ingresado es incorrecto");
		}
		this.porcentaje = porcentaje;
		
	}

	/**
	 * post: Se le resta el porcetaje de descuento al costo total.
	 */
	public void aplicarPromocionPorcentual() {
		double costoTotal = 0;
		for (Atracciones atraccion : this.atracciones) {
			costoTotal += atraccion.costo;
		}
		this.costo = costoTotal -= (costoTotal * this.porcentaje / 100);
	}

	@Override
	public void asignarCosto() {
		this.aplicarPromocionPorcentual();
		
	}

}