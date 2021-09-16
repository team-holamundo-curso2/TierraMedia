package tierraMedia;

import java.util.List;

public class Absolutas extends Promociones {

	public Absolutas(TIPO_DE_ATRACCION tipo, String nombre, TIPO_DE_PROMOCIONES promociones, double costo, int cantidad,
			List<Atracciones> atracciones) {
		super(tipo, nombre, promociones, cantidad, atracciones);
		this.costo = aplicarPromocion(costo);
	}

	/**
	 * post: Modifica el costo de la promocion por el costo descontado que se
	 * establece por el pack.
	 */
	public double aplicarPromocion(double costo) {
		return costo;
	}

}