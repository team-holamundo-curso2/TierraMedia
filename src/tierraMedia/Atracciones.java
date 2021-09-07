package tierraMedia;

import java.util.List;

public class Atracciones extends Producto{



	private LectorAtracciones lector;
	private int cupoDePersonas;
	public static List<Atracciones> atracciones;

	public Atracciones(TIPO_DE_ATRACCION tipo, String nombre, double costo, double tiempo, int cupoDePersonas) {
		super(tipo, nombre, costo, tiempo);
		this.cupoDePersonas = cupoDePersonas;
	}

	public void atraccionesRecibidas() throws AtraccionException {
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
	
	@Override
	public String toString() {
		return "Atracciones [nombre=" + nombre + ", costo=" + costo + ", tiempoDeDuracion=" + tiempoDeDuracion
				+ ", cupoDePersonas=" + cupoDePersonas + ", tipo=" + tipo + "]";
	}

	public static void main(String[] args)  {
		LectorAtracciones atr = new LectorAtracciones();
		Atracciones.atracciones = atr.leerAtracciones();
		System.out.println(atracciones);
	}



}