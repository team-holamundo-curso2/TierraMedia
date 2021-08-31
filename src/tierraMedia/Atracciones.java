package tierraMedia;

public class Atracciones extends Producto {

	private Lector lector;
	private int cupoDePersonas;
	private String nombre;

	public Atracciones(String nombre, double costo, double tiempo, int cupoDePersonas, TIPO_DE_ATRACCION tipo) {
		super(costo, tiempo, tipo);
		this.cupoDePersonas = cupoDePersonas;
		this.nombre = nombre;
	}

	public void atraccionesRecibidas() {
		this.lector.leerAtracciones();
	}

	public boolean hayCupo(int personas) {
		if (personas < cupoDePersonas)
			cupoDePersonas -= personas;
		return true;
	}

	public int obtenerCupoDePersonas() {
		return cupoDePersonas;
	}

	public String obtenerNombre() {
		return nombre;
	}

	public TIPO_DE_ATRACCION obtenerTipo() {
		return super.tipo;
	}
}
