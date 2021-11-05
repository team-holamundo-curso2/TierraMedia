package tierraMedia;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Usuario {
	private int cantidadDeProductosCargados;
	private int idUsuario;
	private double monedas;
	private String nombre;
	private double tiempoDisponible;
	private String preferencia;
	private List<Producto> itinerario = new ArrayList<Producto>();

	public Usuario(int id, String nombre, String preferencia, double monedas, double tiempoDisponible)
			throws UsuarioException {
		if (monedas < 0 || tiempoDisponible < 0) {
			throw new UsuarioException("El usuario ingreso un valor incorrecto");
		}
		if (monedas == 0 || tiempoDisponible == 0) {
			throw new UsuarioException("El usuario tiene no tiene mas monedas o tiempo disponible");
		}
		
		this.idUsuario = id;
		this.nombre = nombre;
		this.preferencia = preferencia;
		this.monedas = monedas;
		this.tiempoDisponible = tiempoDisponible;

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
		uDAO.actualizarMonedasYTiempo(this);
		return true;
	}

	public int obtenerIdUsuario() {
		return idUsuario;
	}

	public void crearItinerario(Producto productosOfrecido) throws AtraccionException {
		this.itinerario.add(productosOfrecido);

	}

	public List<Producto> obtenerItinerario() {
		return this.itinerario;
	}
	
	public List<Producto> consultarItinerario(List<Producto> productos, Usuario user) throws SQLException {
		UsuarioDAO userDAO = new UsuarioDAO();
		if (userDAO.consultaPrimerUso(user) >= 1) {
		user.cargarItinerario(userDAO.filtrarProductos(productos, user));		
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

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", monedas=" + monedas + ", tiempoDisponible=" + tiempoDisponible
				+ ", preferencia=" + preferencia + "]";
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

}
