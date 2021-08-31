package tierraMedia;

import java.util.List;

public class Promociones extends Producto {

	protected TIPO_DE_ATRACCION tipo;
	protected static TIPO_DE_PROMOCIONES promociones;
	private List<Atracciones> atracciones;

	public Promociones(double costo, double tiempo, TIPO_DE_ATRACCION tipo) {
		super(costo, tiempo, tipo);
	}

	public void aplicarPromocion() {
	}

}
