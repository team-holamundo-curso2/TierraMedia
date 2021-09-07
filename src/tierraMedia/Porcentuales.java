package tierraMedia;

import java.util.List;

public class Porcentuales extends Promociones {

	private Double porcentaje;

	public Porcentuales(TIPO_DE_ATRACCION tipo, String nombre, TIPO_DE_PROMOCIONES promociones, double porcentaje,
			int cantidad, List<Atracciones> atracciones) {
		super(tipo, nombre, promociones, cantidad, atracciones);
		this.porcentaje = porcentaje;
		this.costo = aplicarPromocion(porcentaje);
	}

	
	public double aplicarPromocion(double porcentaje) {
		double costoTotal = 0;
		for (Atracciones atraccion : this.atracciones) {
			costoTotal += atraccion.costo;

		}
		return costoTotal -= (costoTotal * this.porcentaje / 100);
	}

}
