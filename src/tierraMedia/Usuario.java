package tierraMedia;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

	private double monedas;
	private String nombre;
	private double tiempoDisponible;
	public TIPO_DE_ATRACCION preferencia;
	List<Producto> itinerario = new ArrayList<Producto>();



	public Usuario() {
	}

	public Usuario(String nombre, TIPO_DE_ATRACCION preferencia, double monedas, double tiempoDisponible) {
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

	public TIPO_DE_ATRACCION obtenerPreferencia() {
		return this.preferencia;
	}

	// METODO BOOLEANO PARA ACEPTAR OFERTAS: RETORNA TRUE Y SETEA LOS ATRIBUTOS DE
	// COSTO Y TIEMPO CON LOS NUEVOS VALORES.
	public boolean aceptar(Producto producto) throws AtraccionException {
		this.monedas -= producto.obtenerCosto();
		this.tiempoDisponible -= producto.obtenerTiempo();
		return true;
	}

	public void crearItinerario(Producto productosOfrecido) throws AtraccionException {
		this.itinerario.add(productosOfrecido);
	}

	public List<Producto> obtenerItinerario() {
		return this.itinerario;
	}

	// METODO PARA OBTENER EL RESUMEN DEL ITINERARIO (NOMBRE DE LOS PRODUCTOS +
	// GASTOS Y TIEMPOS TOTALES)
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
}