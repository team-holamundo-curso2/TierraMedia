package model;

import java.sql.SQLException;
import java.util.Objects;

import persistence.commons.ProductoException;

public abstract class Producto {
	
	protected double costo;
	protected double tiempoDeDuracion;
	protected String tipo;
	protected String nombre;

	public Producto(String tipo, String nombre) {
		this.tipo = tipo;
		this.nombre = nombre;
	}

	public Producto(String tipo, String nombre, double costo) throws ProductoException {
		if (costo <= 0) {
			throw new ProductoException("El producto ingresado tiene un valor incorrecto");
		}

		this.tipo = tipo;
		this.costo = costo;
		this.nombre = nombre;
	}

	public Producto(String tipo, String nombre, double costo, double tiempo) throws ProductoException {
		if (costo <= 0 || tiempo <= 0) {
			throw new ProductoException("El producto ingresado tiene un valor incorrecto");
		}
		this.costo = costo;
		this.tiempoDeDuracion = tiempo;
		this.tipo = tipo;
		this.nombre = nombre;
	}
	
	public abstract int obtenerID();

	public abstract boolean hayCupo();

	public abstract void restarCupo() throws SQLException;
	
	public abstract void reiniciarCupo() throws SQLException;

	public double obtenerCosto() {
		return this.costo;

	}

	public abstract boolean contiene(Producto p);

	public double obtenerTiempo() {
		return this.tiempoDeDuracion;
	}

	public String obtenerTipo() {
		return this.tipo;
	}

	public String obtenerNombre() {
		return this.nombre;
	}

	@Override
	public String toString() {
		return "Producto [costo=" + costo + ", tiempoDeDuracion=" + tiempoDeDuracion + ", tipo=" + tipo + "]";
	}

	public boolean esPromocion() {
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(costo, nombre, tiempoDeDuracion, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return Double.doubleToLongBits(costo) == Double.doubleToLongBits(other.costo)
				&& Objects.equals(nombre, other.nombre)
				&& Double.doubleToLongBits(tiempoDeDuracion) == Double.doubleToLongBits(other.tiempoDeDuracion)
				&& tipo == other.tipo;
	}

}