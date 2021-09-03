package tierraMedia;

import java.util.List;

public abstract class Promociones extends Producto {

	protected TIPO_DE_PROMOCIONES tipoPromo;
	protected List<Atracciones> atracciones;
	protected int cantidadDeAtracciones;
	protected List<Promociones> promos;

	public Promociones(TIPO_DE_ATRACCION tipo, TIPO_DE_PROMOCIONES promociones, int cantidad,
			List<Atracciones> atracciones) {
		super(tipo);
		this.tipoPromo = promociones;
		this.cantidadDeAtracciones = cantidad;
		this.atracciones = atracciones;

	}

	
	public abstract void  aplicarPromocion();{}

	//FALTA DEFINIR UN TOSTRING PARA CUANDO CREEMOS LA LISTA DE PROMOCIONES. PERO PARA QUE ESO QUEDE BIEN
	// PRIMERO HAY QUE TERMINAR DE DEFINRI EL CONSUTRCTOR Y TERMINAR EL LECTOR. 
	
	

}
