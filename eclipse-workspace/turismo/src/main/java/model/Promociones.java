package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public abstract class Promociones extends Producto {

	protected String tipoPromo;
	protected int idPromocion;

	protected List<Atracciones> atracciones;

	public Promociones(int id, String tipo, String nombre, String promociones) {
		super(tipo, nombre);
		this.tipoPromo = promociones;
		this.idPromocion = id;
	}

	public Promociones(String tipo, String nombre, String promociones) {
		super(tipo, nombre);
		this.tipoPromo = promociones;
	}

	public void asignarListaAtracciones(List<Atracciones> atrac) {
		this.atracciones = atrac;

	}

	public abstract void asignarCosto();

	@Override
	public double obtenerTiempo() {
		double tiempoTotal = 0;
		for (Atracciones atraccion : this.atracciones) {
			tiempoTotal += atraccion.obtenerTiempo();
		}
		return tiempoTotal;
	}

	// Metodo para retornar los nombres de las atracciones de una promocion.
	public List<String> obtenerAtracciones() {
		List<String> nombres = new ArrayList<String>();
		for (Atracciones atraccion : this.atracciones) {
			nombres.add(atraccion.obtenerNombre());
		}
		return nombres;
	}

	public String obtenerAtraccion(int posicion) {
		return this.atracciones.get(posicion).obtenerNombre();
	}

	public String obtenerTipoPromo() {
		return tipoPromo;
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

	public void restarCupo() throws SQLException {
		for (Atracciones atraccion : this.atracciones) {
			atraccion.restarCupo();
		}
	}
	
	public void reiniciarCupo() throws SQLException {
		for (Atracciones atraccion : this.atracciones) {
			atraccion.reiniciarCupo();
		}
	}

	@Override
	public boolean contiene(Producto p) {
		boolean contiene = false;
		Iterator<Atracciones> iterador = this.atracciones.listIterator();
		while (!contiene && iterador.hasNext()) {
			contiene = p.contiene(iterador.next());
		}
		return contiene;
	}

	@Override
	public String toString() {
		return "[Nombre=" + nombre + ", tipoPromo=" + tipoPromo + ", Atracciones=" + this.obtenerAtracciones() + "]";
	}

	@Override
	public boolean esPromocion() {
		return true;
	}

	public int obtenerID() {
		return this.idPromocion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(idPromocion);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Promociones other = (Promociones) obj;
		return idPromocion == other.idPromocion;
	}
}