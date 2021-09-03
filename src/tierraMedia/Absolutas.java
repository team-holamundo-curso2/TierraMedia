package tierraMedia;

import java.util.List;

public class Absolutas extends Promociones {

	public Absolutas(TIPO_DE_ATRACCION tipo, TIPO_DE_PROMOCIONES promociones, int cantidad, double costo,
			List<Atracciones> atracciones) {
		super(tipo, promociones, cantidad, atracciones);
		this.aplicarPromocion();

	}
	

	@Override
	public void aplicarPromocion() {
		
		
	}

}
