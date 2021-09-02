package tierraMedia;

import java.util.ArrayList;
import java.util.List;

public class Porcentuales extends Promociones{
	
	//Las preguntas de los if sobre los que van en la clase porcentuales no deberian  ir en el momento que se los crea, o
	//o sea en el archivo de lector de promociones
	public Porcentuales(TIPO_DE_ATRACCION tipo, TIPO_DE_PROMOCIONES promociones, int cantidad,
			List<Atracciones> atracciones) {
		super(tipo, promociones, cantidad, atracciones);
		if (this.tipoPromo == TIPO_DE_PROMOCIONES.PORCENTUALES) {
			this.costo = this.aplicarPromocion();
		}
	}

	public double aplicarPromocion(double porcentaje) {
		double costoTotal = 0;
		double tiempoTotal = 0;
		List<String> nuevasA = new ArrayList<String>();

		for (Atracciones atraccion : this.atracciones) {
			nuevasA.add(atraccion.obtenerNombre());
			if (atraccion.tipo == this.tipo && !nuevasA.contains(atraccion.obtenerNombre())) {
				for (int i = 1; i == this.cantidadDeAtracciones; i++) {
					costoTotal += atraccion.costo;
					tiempoTotal += atraccion.tiempoDeDuracion;
			}
			}
		}
		this.tiempoDeDuracion = tiempoTotal;
		return costoTotal * (1 + (porcentaje / 100));
	}
}
