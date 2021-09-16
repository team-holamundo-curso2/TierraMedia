package tierraMedia;

import java.util.List;

public class AXB extends Promociones {

	public AXB(TIPO_DE_ATRACCION tipo, String nombre, TIPO_DE_PROMOCIONES promociones, int cantidad,
			List<Atracciones> atracciones) {
		super(tipo, nombre, promociones, cantidad, atracciones);
		this.costo = this.aplicarPromocion();

	}

	/**
	 * post:Al costo total se le resta el costo de la última atracción de la promo.
	 */
	public double aplicarPromocion() {
		double costoTotal = 0;
		for (int i = 0; i < (this.atracciones.size() - 1); i++) {
			costoTotal += this.atracciones.get(i).obtenerCosto();
		}
		return costoTotal;
	}

}