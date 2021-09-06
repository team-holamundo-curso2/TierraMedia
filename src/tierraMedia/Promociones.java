package tierraMedia;

import java.util.ArrayList;
import java.util.List;

public abstract class Promociones extends Producto {

	protected TIPO_DE_PROMOCIONES tipoPromo;
	protected List<Atracciones> atracciones;
	protected int cantidadDeAtracciones;

	public Promociones(TIPO_DE_ATRACCION tipo, String nombre, double costo, TIPO_DE_PROMOCIONES promociones,
			int cantidad, List<Atracciones> atracciones) {
		super(tipo, nombre, costo);
		this.tipoPromo = promociones;
		this.cantidadDeAtracciones = cantidad;
		this.atracciones = atracciones;

	}

	public Promociones(TIPO_DE_ATRACCION tipo, String nombre, TIPO_DE_PROMOCIONES promociones,
			int cantidad, List<Atracciones> atracciones) {
		super(tipo, nombre);
		this.tipoPromo = promociones;
		this.cantidadDeAtracciones = cantidad;
		this.atracciones = atracciones;

	}

	public double aplicarPromocion(double costo) {
		return costo;
	}
	
	//Calcula la suma de los tiempos
	@Override
	public double obtenerTiempo() {
		double tiempoTotal = 0;
		for(Atracciones atraccion : this.atracciones) {
			tiempoTotal += atraccion.obtenerTiempo();
		}
		return tiempoTotal ;
	}
	
	
	
	

	@Override
	public String toString() {
		return "Promociones [tipo=" + tipo + ", tipoPromo=" + tipoPromo + ", cantidadDeAtracciones="
				+ cantidadDeAtracciones + ", costo=" + costo + ", Atracciones=" + this.atracciones + "]";

	}

	public static void main(String[] args) {
		List<Atracciones> atracciones = new ArrayList<Atracciones>();
		List<Promociones> promos = new ArrayList<Promociones>();

		LectorAtracciones atr = new LectorAtracciones();
		atracciones = atr.leerAtracciones();

		LectorPromociones prom = new LectorPromociones();
		promos = prom.leerPromociones(atracciones);
		System.out.println(promos);

	}

}