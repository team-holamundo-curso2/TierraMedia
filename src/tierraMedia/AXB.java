package tierraMedia;

import java.util.ArrayList;
import java.util.List;

public class AXB extends Promociones {
	

	@Override
	public String toString() {
		return "AXB [tipo=" + tipo + ", tipoPromo=" + tipoPromo + ", cantidadDeAtracciones=" + cantidadDeAtracciones
				+ ", Atracciones=" + this.atracciones + "]";
	}

	public AXB(TIPO_DE_ATRACCION tipo, String nombre, TIPO_DE_PROMOCIONES promociones, int cantidad,
			List<Atracciones> atracciones) {
		super(tipo, nombre, promociones, cantidad, atracciones);
		this.costo = this.aplicarPromocion();

	}

	//Le tengo que restar la atraccion gratuita
	public double aplicarPromocion() {
		double costoTotal = 0;

		List<String> nuevasA = new ArrayList<String>();

		for (Atracciones atraccion : this.atracciones) {
			nuevasA.add(atraccion.obtenerNombre());
			if (atraccion.tipo == this.tipo && !nuevasA.contains(atraccion.obtenerNombre())) {
				for (int i = 1; i < this.cantidadDeAtracciones; i++) {
					costoTotal += atraccion.costo;
				}
			}
		}
		
		return costoTotal ;
	}

	public String obtenerAtracciones(List<Promociones> promos) {
		return null;
	}

}