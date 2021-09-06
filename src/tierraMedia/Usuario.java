package tierraMedia;


import java.util.List;
import java.util.Scanner;

public class Usuario {

	private double monedas;
	private String nombre;
	private double tiempoDisponible;
	public TIPO_DE_ATRACCION preferencia;
	private List<Producto> itinerario;

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


	public void ofertasAceptadas(List<Producto> productosAOfrecer) {

		for (Producto ofrecer : productosAOfrecer) {
			while (this.monedas > ofrecer.costo && this.tiempoDisponible > ofrecer.tiempoDeDuracion) {
				if(ofertarProducto(ofrecer))
				aceptar(ofrecer);
				itinerario.add(ofrecer);
			}
		}

	}

	//Te devuelve true si acepta la oferta.
	public boolean ofertarProducto(Producto productoAofrecer) {
		String eleccion = "";
		Scanner respuesta = new Scanner(System.in);

		System.out.println("¿Acepta agregar a su itinerario" + productoAofrecer.nombre + "?");
		System.out.println("Si desea aceptar la oferta responda Si, en caso contrario escriba No");
		eleccion = respuesta.next().toUpperCase();
		System.out.println();
		while (!eleccion.equals("SI") && !eleccion.equals("NO")) {
			System.out.println("Ingrese Si o No");
			eleccion = respuesta.next().toUpperCase();
		}
		respuesta.close();
		return eleccion.equals("SI");
	}

	public List<Producto> obtenerItinerario() {
		System.out.println(this.itinerario); // Imprime por pantalla
		return this.itinerario; // Retorna el itinerario.
	}
	
	

	public String resumenItinerario() { // RESUMEN DEL GASTO TOTAL Y EL TIEMPO TOTAL DE SU ITINERARIO
		double costoTotal = 0;
		double tiempoTotal = 0;
		for (Producto suma : this.itinerario) {
			costoTotal += suma.costo;
			tiempoTotal += suma.tiempoDeDuracion;
		}
		return "Costo Total =" + costoTotal + ", Tiempo Total =" + tiempoTotal;

	}

	public boolean aceptar(Producto producto) {
		this.monedas -= producto.costo;
		this.tiempoDisponible -= producto.tiempoDeDuracion;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [monedas=" + monedas + ", nombre=" + nombre + ", tiempoDisponible=" + tiempoDisponible
				+ ", preferencia=" + preferencia + "]";
	}
}