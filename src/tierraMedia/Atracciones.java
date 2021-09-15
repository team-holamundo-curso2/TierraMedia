package tierraMedia;

import java.util.List;

public class Atracciones extends Producto {

	private int cupoDePersonas;
	public static List<Atracciones> atracciones;

	public Atracciones(TIPO_DE_ATRACCION tipo, String nombre, double costo, double tiempo, int cupoDePersonas) {
		super(tipo, nombre, costo, tiempo);
		this.cupoDePersonas = cupoDePersonas;
	}

	@Override
	public boolean hayCupo() {
		return this.cupoDePersonas > 0;
	}

	public void restarCupo() {
		this.cupoDePersonas--;
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

	@Override
	public boolean contiene(Producto p) {
		if (p.esPromocion()) {
			return p.contiene(this);
		}
		return this.equals(p);
		
	}

}