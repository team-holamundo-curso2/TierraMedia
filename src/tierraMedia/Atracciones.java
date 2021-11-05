package tierraMedia;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class Atracciones extends Producto {

	private int cupoDePersonas;
	public static List<Atracciones> atracciones;
	protected int idAtraccion;

	public Atracciones(int id, String tipo, String nombre, double costo, double tiempo, int cupoDePersonas) {
		super(tipo, nombre, costo);
		this.idAtraccion = id;
		this.tiempoDeDuracion = tiempo;
		this.cupoDePersonas = cupoDePersonas;
	}

	@Override
	public boolean hayCupo() {
		return this.cupoDePersonas > 0;
	}

	public void restarCupo() throws SQLException {
		this.cupoDePersonas--;
		AtraccionesDAO atrDAO = new AtraccionesDAO();
		atrDAO.actualizarCupo(this);
	}

	public void reiniciarCupo() throws SQLException {
		this.cupoDePersonas++;
		AtraccionesDAO atrDAO = new AtraccionesDAO();
		atrDAO.restaurarCupo(this);
	}

	public int obtenerCupoDePersonas() {
		return cupoDePersonas;
	}

	public String obtenerNombre() {
		return nombre;
	}

	public String obtenerTipo() {
		return super.tipo;
	}

	@Override
	public String toString() {
		return "Atracciones [nombre=" + nombre + ", costo=" + costo + ", tiempoDeDuracion=" + tiempoDeDuracion
				+ ", cupoDePersonas=" + cupoDePersonas + ", tipo=" + tipo + "]";
	}

	@Override
	public boolean contiene(Producto p) {
		if (p.esPromocion()) {
			return p.contiene(this);
		}
		return this.equals(p);

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(idAtraccion);
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
		Atracciones other = (Atracciones) obj;
		return idAtraccion == other.idAtraccion;
	}

	public int obtenerID() {
		return this.idAtraccion;
	}

}
