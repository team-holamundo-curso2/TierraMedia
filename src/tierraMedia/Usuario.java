package tierraMedia;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Usuario {

	private int idUsuario;
	private int cantidadProductosAceptados; 
	private double monedas;
	private String nombre;
	private double tiempoDisponible;
	private String preferencia;
	private List<Producto> itinerario = new ArrayList<Producto>();

	public Usuario(int id, String nombre, String preferencia, double monedas, double tiempoDisponible) throws UsuarioException {
		if (monedas <= 0 || tiempoDisponible <= 0) {
			throw new UsuarioException("El usuario ingreso un valor incorrecto");
		}
		this.idUsuario = id;
		this.nombre = nombre;
		this.preferencia = preferencia;
		this.monedas = monedas;
		this.tiempoDisponible = tiempoDisponible;

	}
		
	public void contarProductos() {
		this.cantidadProductosAceptados = this.itinerario.size();
		
	}

	public String obtenerNombre() {
		return this.nombre;
	}

	public double obtenerMonedas() {
		return this.monedas;
	}

	public double obtenerTiempoDisponible() {
		return this.tiempoDisponible;
	}

	public String obtenerPreferencia() {
		return this.preferencia;
	}

	// UNIR LOS UPGRADE EN 1 SOLO
	public boolean aceptar(Producto producto) throws AtraccionException, SQLException {
		this.monedas -= producto.obtenerCosto();
		this.tiempoDisponible -= producto.obtenerTiempo();
		UsuarioDAO uDAO = new UsuarioDAO();
		uDAO.actualizarMonedas(this);
		uDAO.actualizarTiempo(this);		
		return true;
	}
	
	public int obtenerIdUsuario() {
		return idUsuario;
	}

	public void crearItinerario(Producto productosOfrecido) throws AtraccionException {
		this.itinerario.add(productosOfrecido);
		this.contarProductos();
		
	}

	public List<Producto> obtenerItinerario() {
		return this.itinerario;
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

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", monedas=" + monedas + ", tiempoDisponible=" + tiempoDisponible
				+ ", preferencia=" + preferencia + "]";
	}

	public void setItinerario(List<Producto> itinerario) {
		this.itinerario = itinerario;
	}

	public int obtenerCantidadProductosAceptados() {
		return cantidadProductosAceptados;
	}

}