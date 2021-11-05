package tierraMedia;

import java.util.Comparator;

public class OfertablesPorPreferencia implements Comparator<Producto> {

	private String preferencia;

	public OfertablesPorPreferencia(String tipo) {
		this.preferencia = tipo;
	}

	@Override
	public int compare(Producto o1, Producto o2) {
		if (o1.tipo.equals(o2.tipo)) {
			// ambas son preferidas, compara por lo siguiente (promo)
			if (o1.esPromocion() && o2.esPromocion()) {
				// ambas son promos, compara costo
				return compararPorPrioridad(o1, o2);
			} else if (!o1.esPromocion() && !o2.esPromocion()) {
				return compararPorPrioridad(o1, o2);
			} else {
				return -Boolean.compare(o1.esPromocion(), o2.esPromocion());
			}
		} else if (o1.tipo != this.preferencia && o2.tipo != this.preferencia) {
			if (o1.esPromocion() && o2.esPromocion()) {
				return compararPorPrioridad(o1, o2);
			} else if (!o1.esPromocion() && !o2.esPromocion()) {
				return compararPorPrioridad(o1, o2);
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

	private int compararPorPrioridad(Producto o1, Producto o2) {
		if (Double.compare(o1.costo, o2.costo) == 0) {
			// mismo costo, comparo por tiempo finalmente
			return -Double.compare(o1.tiempoDeDuracion, o2.tiempoDeDuracion);
		} else {
			return -Double.compare(o1.costo, o2.costo);
		}
	}
}