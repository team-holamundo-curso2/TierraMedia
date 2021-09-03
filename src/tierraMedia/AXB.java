package tierraMedia;

import java.util.ArrayList;
import java.util.List;

public class AXB extends Promociones {

	public AXB(TIPO_DE_ATRACCION tipo, TIPO_DE_PROMOCIONES promociones, int cantidad, double costo,
			List<Atracciones> atracciones) {
		super(tipo, promociones, cantidad, atracciones);
		
		this.aplicarPromocion();
		
	}

	@Override
	public void aplicarPromocion() {
		double costoTotal = 0;
		double tiempoTotal = 0;
		List<String> nuevasA = new ArrayList<String>();

		for (Atracciones atraccion : this.atracciones) {
			nuevasA.add(atraccion.obtenerNombre());
			if (atraccion.tipo == this.tipo && !nuevasA.contains(atraccion.obtenerNombre())) {
				for (int i = 1; i < this.cantidadDeAtracciones; i++) {
					costoTotal += atraccion.costo;
				}
				tiempoTotal += atraccion.tiempoDeDuracion;
			}
		}
		this.tiempoDeDuracion = tiempoTotal;
		this.costo = costoTotal;
	}

}
