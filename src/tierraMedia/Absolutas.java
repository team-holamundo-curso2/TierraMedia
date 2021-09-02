package tierraMedia;

import java.util.List;

public class Absolutas extends Promociones {

	public Absolutas(TIPO_DE_ATRACCION tipo, TIPO_DE_PROMOCIONES promociones, int cantidad, double costo,
			List<Atracciones> atracciones) {
		super(tipo, promociones, cantidad, atracciones);
		if (this.tipoPromo == TIPO_DE_PROMOCIONES.ABSOLUTAS) {
			this.costo = this.aplicarPromocion(costo);
		}
	}

	public double aplicarPromocion(double costo) {
		return costo;
	}

}
