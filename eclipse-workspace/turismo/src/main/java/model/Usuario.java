package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import persistence.commons.AtraccionException;
import persistence.commons.UsuarioException;
import persistence.impl.UsuarioDAOImpl;

public class Usuario {

	private List<Producto> itinerario = new ArrayList<Producto>();
	private HashMap<String, String> errors;
	private Integer idUsuario;
	private String nombre;
	private Boolean admin;
	private Double monedas;
	private Double tiempoDisponible;
	private Integer preferencia;
	private Boolean borrado;
	private int cantidadDeProductosCargados; /* VER */

	/* CONSULTAR POR MANEJO DE EXCEPTION */
	public Usuario(int id, String nombre, int preferencia, double monedas, double tiempoDisponible, Boolean admin,
			Boolean borrado) throws UsuarioException {

		this.idUsuario = id;
		this.nombre = nombre;
		this.preferencia = preferencia;
		this.monedas = monedas;
		this.tiempoDisponible = tiempoDisponible;
		this.admin = admin;
		this.borrado = borrado;
	}

	public void aceptar(Producto producto) throws AtraccionException, SQLException {
		this.monedas -= producto.obtenerCosto();
		this.tiempoDisponible -= producto.obtenerTiempo();
		this.itinerario.add(producto);
		UsuarioDAOImpl uDAO = new UsuarioDAOImpl();
		uDAO.actualizarItinerario(this);
	}

	public boolean puedePagarlo(Producto attraction) {
		return attraction.obtenerCosto() <= this.monedas;
	}

	public boolean tieneTiempo(Atracciones attraction) {
		return attraction.obtenerTiempo() <= this.tiempoDisponible;
	}

	public Boolean esAdmin() {
		return admin;
	}

	public Double obtenerMonedas() {
		return monedas;
	}

	public Integer obtenerId() {
		return idUsuario;
	}

	public Double obtenerTiempo() {
		return tiempoDisponible;
	}

	public String obtenerNombre() {
		return nombre;
	}

	public boolean isNull() {
		return false;
	}

	public void establecerAdmin(Boolean admin) {
		this.admin = admin;
	}

	public void establecerMonedas(Double coins) {
		this.monedas = coins;
	}

	public void establecerId(Integer id) {
		this.idUsuario = id;
	}

	public void establecerTiempo(Double time) {
		this.tiempoDisponible = time;
	}

	public void establecerNombre(String username) {
		this.nombre = username;
	}

	public Integer obtenerPreferencia() {
		return this.preferencia;
	}

	public void agregarAItinerario(Producto productosOfrecido) throws AtraccionException, SQLException {
		this.aceptar(productosOfrecido);

	}

	public List<Producto> obtenerItinerario() {
		return this.itinerario;
	}

	public List<Producto> consultarItinerario(List<Atracciones> productos, Usuario user) throws SQLException {
		UsuarioDAOImpl uDAO = new UsuarioDAOImpl();
		if (uDAO.consultaPrimerUso(user) >= 1) {
			user.cargarItinerario(uDAO.filtrarProductos(productos, user));
		}
		return user.obtenerItinerario();
	}

	public String resumenItinerario() {
		double costoTotal = 0;
		double tiempoTotal = 0;
		for (Producto suma : this.itinerario) {
			costoTotal += suma.obtenerCosto();
			tiempoTotal += suma.obtenerTiempo();
		}
		return "Costo Total =" + costoTotal + ", Tiempo Total =" + tiempoTotal;

	}

	public void cargarItinerario(List<Producto> itinerario) {
		this.itinerario = itinerario;
		if (this.itinerario != null) {
			this.cantidadDeProductosCargados = this.itinerario.size();
		}
	}

	public int obtenerCantidadProductosPreCargados() {
		return this.cantidadDeProductosCargados;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idUsuario);
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", monedas=" + monedas + ", tiempoDisponible=" + tiempoDisponible
				+ ", preferencia=" + preferencia + ", admin=" + admin + "]";
	}

	public Boolean getBorrado() {
		return borrado;
	}

	public void setBorrado(Boolean borrado) {
		this.borrado = borrado;
	}

	public void validar() {
		errors = new HashMap<String, String>();

		if (monedas < 0) {
			errors.put("monedas", "No debe ser negativo");
		}
		if (tiempoDisponible < 0) {
			errors.put("tiempo", "No debe ser negativo");
		}
	}

	public boolean esValido() {
		validar();
		return errors.isEmpty();
	}

	public Map<String, String> getErrors() {

		return errors;
	}

}
