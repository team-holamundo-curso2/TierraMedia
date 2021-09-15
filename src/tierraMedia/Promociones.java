package tierraMedia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Promociones extends Producto {

	protected TIPO_DE_PROMOCIONES tipoPromo;
	protected List<Atracciones> atracciones;
	protected int cantidadDeAtracciones;

	public Promociones(TIPO_DE_ATRACCION tipo, String nombre, double costo, TIPO_DE_PROMOCIONES promociones,
			int cantidad, List<Atracciones> atracciones) {
		super(tipo, nombre, costo);
		this.tipoPromo = promociones;
		this.cantidadDeAtracciones = cantidad;
		this.atracciones = atracciones;

	}

	public Promociones(TIPO_DE_ATRACCION tipo, String nombre, TIPO_DE_PROMOCIONES promociones, int cantidad,
			List<Atracciones> atracciones) {
		super(tipo, nombre);
		this.tipoPromo = promociones;
		this.cantidadDeAtracciones = cantidad;
		this.atracciones = atracciones;

	}

	public double aplicarPromocion(double costo) {
		return costo;
	}

	// Calcula la suma de los tiempos
	@Override
	public double obtenerTiempo() {
		double tiempoTotal = 0;
		for (Atracciones atraccion : this.atracciones) {
			tiempoTotal += atraccion.obtenerTiempo();
		}
		return tiempoTotal;
	}

	// ARMAR METODO DONDE LE RESTE CUPO A LAS ATRACCIONES QUE TENGA

	public List<String> obtenerAtracciones(List<Atracciones> atracciones) {
		List<String> nombres = new ArrayList<String>();
		for (Atracciones atraccion : this.atracciones) {
			nombres.add(atraccion.obtenerNombre());
		}
		return nombres;
	}

	@Override
	public boolean hayCupo() {
		boolean hay = true;
		for (Atracciones atraccion : this.atracciones) {
			if (!atraccion.hayCupo()) {
				hay = false;
			}
		}
		return hay;
	}

	public void restarCupo() {
		for (Atracciones atraccion : this.atracciones) {
			atraccion.restarCupo();
		}
	}

	// TERMINARLO
	@Override
	public boolean contiene(Producto p) {
		boolean contiene = false;
		Iterator<Atracciones> iterador = this.atracciones.listIterator();
		while (!contiene && iterador.hasNext()) {
			contiene=p.contiene(iterador.next());
		}
	return contiene;
	}

	@Override
	public String toString() {
		return "[Nombre=" + nombre + ", tipoPromo=" + tipoPromo + ", cantidadDeAtracciones=" + cantidadDeAtracciones
				+ ", Atracciones=" + this.obtenerAtracciones(this.atracciones) + "]";
	}

	@Override
	public boolean esPromocion() {
		return true;
	}
}