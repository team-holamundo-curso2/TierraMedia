package tierraMedia;

import java.util.ArrayList;
import java.util.List;

public class Porcentuales extends Promociones {
	@Override
	public String toString() {
		return "Porcentuales [tipo=" + tipo + ", tipoPromo=" + tipoPromo
				+ ", porcentaje=" + porcentaje +", cantidadDeAtracciones=" + cantidadDeAtracciones + ", Atracciones=" + this.atracciones + "]";
	}

	private Double porcentaje;

	
	public Porcentuales(TIPO_DE_ATRACCION tipo, String nombre, TIPO_DE_PROMOCIONES promociones, double porcentaje, int cantidad,
			List<Atracciones> atracciones) {
		super(tipo, nombre, promociones, cantidad, atracciones);
		this.porcentaje = porcentaje;
		this.costo = aplicarPromocion(porcentaje);
	}

	public double aplicarPromocion(double porcentaje) {
		double costoTotal = 0;
		List<String> nuevasA = new ArrayList<String>();

		for (Atracciones atraccion : this.atracciones) {
			nuevasA.add(atraccion.obtenerNombre());
			if (atraccion.tipo == this.tipo && !nuevasA.contains(atraccion.obtenerNombre())) {
				for (int i = 1; i == this.cantidadDeAtracciones; i++) {
					costoTotal += atraccion.costo;

				}
			}
		}

		return costoTotal * (1 + (this.porcentaje / 100));
	}

	public String obtenerAtracciones(List<Promociones> promos) {		
		return null;
	}

	public static void main(String[] args) {
		
		
		
	}
}