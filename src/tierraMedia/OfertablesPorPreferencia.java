package tierraMedia;

import java.util.Comparator;

import java.util.Comparator;

public class OfertablesPorPreferencia implements Comparator<Producto> {

//Falta pasar a TIPO_de_ATRACCION los string
	
	private String preferencia;

	public OfertablesPorPreferencia(String tipo) {
		this.preferencia = tipo;
	}

	// ME TIRA ERROR DE INCOMPATIBILIDAD DENTRE EL ENUM Y STRING.
	@Override
	public int compare(Producto o1, Producto o2) {
		if (o1.tipo == this.preferencia && o2.tipo == this.preferencia) {
			// ambas son preferidas, compara por lo siguiente (promo)
			if (o1.esPromocion() && o2.esPromocion()) {
				// ambas son promos, compara costo
				if (Double.compare(o1.costo, o2.costo) == 0) {
					// mismo costo, comparo por tiempo finalmente
					return -Double.compare(o1.tiempoDeDuracion, o2.tiempoDeDuracion);
				} else {
					return -Double.compare(o1.costo, o2.costo);
				}
			} else {
				return -Boolean.compare(o1.esPromocion(), o2.esPromocion());
			}
		} else if (o1.tipo != this.preferencia && o2.tipo != this.preferencia) {
			if (o1.esPromocion() && o2.esPromocion()) {
				// ambas son promos, compara por el costo
				if (Double.compare(o1.costo, o2.costo) == 0) {
					// mismo costo, comparto por tiempo finalmente
					return -Double.compare(o1.tiempoDeDuracion, o2.tiempoDeDuracion);
				} else {
					return -Double.compare(o1.costo, o2.costo);
				}
			} else if (!o1.esPromocion() && !o2.esPromocion()) {
				// ninguna es promo, compara por costo
				if (Double.compare(o1.costo, o2.costo) == 0) {
					// mismo costo, comparo los tiempos finalmente
					return -Double.compare(o1.tiempoDeDuracion, o2.tiempoDeDuracion);
				} else {
					return -Double.compare(o1.costo, o2.costo);
				}
			} else {
				return -Boolean.compare(o1.esPromocion(), o2.esPromocion());
			}
		} else {
			// una es preferida y la otra no
			if (o1.tipo == this.preferencia)
				return -1;
			return 1;
		}
	}
}

