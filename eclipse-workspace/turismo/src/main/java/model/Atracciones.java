package model;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import persistence.impl.AtraccionDAOImpl;

public class Atracciones extends Producto {

	private Integer idAtraccion;
	private Double tiempo;
	private Integer cupoDePersonas;
	private String descripcion;
	

	/* MAPA DE ERRORES - CAPTURAR POR EXCEPTION?? */
	private Map<String, String> errores;

	public Atracciones(Integer id, String tipo, String nombre, Double costo, Double duration, Integer capacity, String descripcion) {
		super(tipo, nombre, costo);
		this.descripcion = descripcion;
		this.idAtraccion = id;
		this.tiempo = duration;
		this.cupoDePersonas = capacity;
	}

	public boolean esValido() {
		validar();
		return errores.isEmpty();
	}

	public void validar() {
		errores = new HashMap<String, String>();

		if (costo <= 0) {
			errores.put("cost", "Debe ser positivo");
		}
		if (tiempo <= 0) {
			errores.put("duration", "Debe ser positivo");
		}
		if (cupoDePersonas <= 0) {
			errores.put("capacity", "Debe ser positivo");
		}
	}

	public Map<String, String> obtenerErrores() {
		return errores;
	}

	public Integer obtenerId() {
		return idAtraccion;
	}
	
	public double obtenerTiempo() {
		return tiempo;
	}

	public void establecerId(Integer id) {
		this.idAtraccion = id;
	}

	public String obtenerNombre() {
		return nombre;
	}
	
	public String obtenerDescripcion() {
		return descripcion;
	}

	public void establecerNombre(String name) {
		this.nombre = name;
	}
	
	public void establecerDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer obtenerCupo() {
		return cupoDePersonas;
	}

	public void obtenerCupoDePersonas(Integer capacity) {
		this.cupoDePersonas = capacity;
	}

	@Override
	public String toString() {
		return "Atracciones [nombre=" + nombre + ", costo=" + costo + ", tiempoDeDuracion=" + tiempoDeDuracion
				+ ", cupoDePersonas=" + cupoDePersonas + ", tipo=" + tipo + "]";
	}

	@Override
	public boolean hayCupo() {
		return this.cupoDePersonas > 0;
	}

	public void restarCupo() throws SQLException {
		this.cupoDePersonas--;
		AtraccionDAOImpl atrDAO = new AtraccionDAOImpl();
		atrDAO.actualizarCupo(this);
	}
	
	public void reiniciarCupo() throws SQLException {
		this.cupoDePersonas++;
		AtraccionDAOImpl atrDAO = new AtraccionDAOImpl();
		atrDAO.restaurarCupo(this);
	}

	public String obtenerTipo() {
		return super.tipo;
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

	public void establecerCosto(Integer cost) {
		this.costo = cost;
		
	}

	public void establecerTiempo(Double duration2) {
		this.tiempoDeDuracion = duration2;
	}
}
