package tierraMedia;

import java.util.List;
import java.util.Scanner;

public class Usuario {

	private int monedas;
	private String nombre;
	private int tiempoDisponible;
	public TIPO_DE_ATRACCION preferencia;
	private List<Producto> itinerario;

	public Usuario() {
	}

	public Usuario(String nombre, TIPO_DE_ATRACCION preferencia, int monedas, int tiempoDisponible) {
		this.nombre = nombre;
		this.preferencia = preferencia;
		this.monedas = monedas;
		this.tiempoDisponible = tiempoDisponible;

	}

	// FALTA AGREGAR EXCEPTION y AMOLDAR A LAS NUEVAS COLECCIONES
	// EL SCANNER LO ARME DE ESTA FORMA PORQUE ES COMO LO FUE DEFINIENDO EL PROFE
	// DEL TEORICO + ALGUNA GILADA QUE VI POR INTERNET
	// PERO NO SÉ SI REALMENTE FUNCIONA ASI, NO PUDE INVESTIGAR MUCHO MAS.

	public void ofertasAceptadas(List<Producto> productosAOfrecer) {
		// ESTO DE SCANNER NO SÉ SI VA ACÁ
		String entradaTeclado = "";
		Scanner entradaEscaner = new Scanner(System.in); // Creación de un objeto Scanner
		entradaTeclado = entradaEscaner.nextLine(); // Invocamos un método sobre un objeto Scanner

		// iMPRIMIR LA PREGUNTA (oferta). y revisar orden de oferta.
		for (Producto ofrecer : productosAOfrecer) {

			while (this.monedas > ofrecer.costo && this.tiempoDisponible > ofrecer.tiempoDeDuracion) { // HAY QUE
																										// AGREGAR
				if (entradaTeclado == "Si") { // LA

					aceptar(ofrecer);
					itinerario.add(ofrecer);
				}
			}
		}
		entradaEscaner.close();
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
