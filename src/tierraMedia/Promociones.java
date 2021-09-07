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
	
	public List<String> obtenerAtracciones(List<Atracciones> atracciones) {
		List<String> nombres = new ArrayList<String>();
		for (Atracciones atraccion : atracciones) {
			nombres.add(atraccion.obtenerNombre());
		}
		return nombres;
	}
	
	@Override
	public String toString() {
		return "[Nombre=" + nombre + ", tipoPromo=" + tipoPromo + ", cantidadDeAtracciones=" + cantidadDeAtracciones
				+ ", Atracciones=" + this.obtenerAtracciones(this.atracciones) + "]";
	}
	
	
	
	@Override
	public boolean esPromocion() {
		return true;
	}

	public static void main(String[] args) {
		List<Atracciones> atracciones = new ArrayList<Atracciones>();
		List<Promociones> promos = new ArrayList<Promociones>();

		LectorAtracciones atr = new LectorAtracciones();
		atracciones = atr.leerAtracciones();

		LectorPromociones prom = new LectorPromociones();
		promos = prom.leerPromociones(atracciones);
		System.out.println(promos);

		System.out.println("____________1_______________");
		
		System.out.println(promos);
		System.out.println(promos.get(0).obtenerCosto());
		System.out.println(promos.get(0).aplicarPromocion(20));
		System.out.println(promos.get(0).obtenerTiempo());
		
System.out.println("____________2_______________");
		
		System.out.println(promos.get(2));
		System.out.println(promos.get(2).obtenerCosto());
		System.out.println(promos.get(2).obtenerTiempo());
		
System.out.println("____________3_______________");
		
		System.out.println(promos.get(1));
		System.out.println(promos.get(1).obtenerCosto());
		System.out.println(promos.get(1).obtenerTiempo());
		
System.out.println("____________4_______________");
		
			
				
	}

}