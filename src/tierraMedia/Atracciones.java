package tierraMedia;

import java.util.List;

public class Atracciones extends Producto {

	private Lector lector;
	private int cupoDePersonas;
	private String nombre;
	private static List<Atracciones> atracciones;

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
	
	public static void main(String[] args) {
		LectorAtracciones atr = new LectorAtracciones();
		Atracciones.atracciones = atr.leerAtracciones();
		System.out.println(atracciones);
	}
}
