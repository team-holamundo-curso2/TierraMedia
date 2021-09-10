package tierraMedia;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Usuario {

	private double monedas;
	private String nombre;
	private double tiempoDisponible;
	public TIPO_DE_ATRACCION preferencia;
	List<Producto> itinerario;

//Falta mudar algunos metodos para la clase ofertable. 

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

	public String obtenerPreferencia() {
		return this.preferencia.name();
	}
	

	// METODO BOOLEANO PARA ACEPTAR OFERTAS: RETORNA TRUE Y SETEA LOS ATRIBUTOS DE
	// COSTO Y TIEMPO CON LOS NUEVOS VALORES.
	public boolean aceptar(Producto producto) throws AtraccionException {
		this.monedas -= producto.obtenerCosto();
		this.tiempoDisponible -= producto.obtenerTiempo();
		return true;
	}
	
	//Quedaria metodo que traiga la lista de ofertas aceptaas y la convierta en su itinerario.
	
	//Metodo aceptar quedaria. (EL que disminuye sus monedas ...)

	/*
	 * Para mudar?
	 */
	// METODO QUE ESTABLECE LAS CONDICIONES PARA USAR "ACEPTAR" Y SETEA EL
	// ITINERARIO.
	public void ofertasAceptadas(List<Producto> productosAOfrecer) throws AtraccionException {
		List<Producto> prod = new ArrayList<Producto>();

		for (Producto ofrecer : productosAOfrecer)  {
			if (this.monedas > ofrecer.costo && this.tiempoDisponible > ofrecer.tiempoDeDuracion) {

				if (ofertarProducto(ofrecer)) {
					aceptar(ofrecer);
					prod.add(ofrecer);
				}
			}
		}

		this.itinerario = prod;
	}

	// METODO BOOLEANO PARA IMPRIMIR POR PANTALLA LAS OFERTAS Y SI EL USUARIO QUIERE
	// O NO ACEPTARLAS.
	public boolean ofertarProducto(Producto productoAofrecer) throws AtraccionException {
		String eleccion = "";
		Scanner respuesta = new Scanner(System.in);

		System.out.println("¿Acepta agregar a su itinerario " + productoAofrecer.nombre + "?");
		System.out.println("Si desea aceptar la oferta responda Si, en caso contrario escriba No");
		
			eleccion = respuesta.next().toUpperCase();
			System.out.println();
			while (!eleccion.equals("SI") && !eleccion.equals("NO")) {
				System.out.println("Ingrese Si o No");
				eleccion = respuesta.next().toUpperCase();
			}
			
			return eleccion.equals("SI");
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
		return  "Costo Total =" + costoTotal + ", Tiempo Total ="
				+ tiempoTotal;

	}
	
	/**
	 * Fin para mudar
	 */

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", monedas=" + monedas + ", tiempoDisponible=" + tiempoDisponible
				+ ", preferencia=" + preferencia + "]";
	}
}