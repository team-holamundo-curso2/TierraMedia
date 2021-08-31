package tierraMedia;

public class Usuario {
	private int monedas;
	private String nombre;
	private int tiempoDisponible;
	public TIPO_DE_ATRACCION preferencia;
	private Producto[] itinerario;

	public Usuario() {
	}

	public Usuario(String nombre, TIPO_DE_ATRACCION preferencia, int monedas, int tiempoDisponible) {
		this.nombre = nombre;
		this.preferencia = preferencia;
		this.monedas = monedas;
		this.tiempoDisponible = tiempoDisponible;

	}

	// FALTA AGREGAR EXCEPTION
	public void ofertasAceptadas(Producto[] productosAOfrecer) {
		for (int i = 0; i < productosAOfrecer.length; i++)// FALTA AGREGAR ==> POR CADA LINEA SI ACEPTA O NO ==> scanner
															// system.in
			if (aceptar(productosAOfrecer)) {
				while (this.monedas > productosAOfrecer[i].costo
						&& this.tiempoDisponible > productosAOfrecer[i].tiempoDeDuracion) {
					this.monedas -= productosAOfrecer[i].costo;
					this.tiempoDisponible -= productosAOfrecer[i].tiempoDeDuracion;
				}
				this.itinerario[i] = productosAOfrecer[i]; // SI ACEPTA, GUARDA EN LISTA ITINERARIO
			}
	}

	public Producto[] getItinerario() {
		System.out.println(this.itinerario); // Imprime por pantalla
		return this.itinerario; // Retorna el itinerario.
	}

	public String resumenItinerario(Producto[] itinerario) { // RESUMEN DEL GASTO TOTAL Y EL TIEMPO TOTAL DE SU
																// ITINERARIO
		int costoTotal = 0;
		int tiempoTotal = 0;
		for (Producto suma : itinerario) {
			costoTotal += suma.costo;
			tiempoTotal += suma.tiempoDeDuracion;
		}
		return "Costo Total =" + costoTotal + ", Tiempo Total =" + tiempoTotal;
	}

	public boolean aceptar(Producto[] productosAOfrecer) {
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [monedas=" + monedas + ", nombre=" + nombre + ", tiempoDisponible=" + tiempoDisponible
				+ ", preferencia=" + preferencia + "]";
	}

}
