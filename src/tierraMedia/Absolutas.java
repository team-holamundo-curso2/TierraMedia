package tierraMedia;

public class Absolutas extends Promociones {
	private double costoPromo;
	
	public Absolutas(int id, String tipo, String nombre, String promociones, double costo) {
		super(id, tipo, nombre, promociones);
		this.costoPromo = costo;
	}

	public void aplicarPromocion() {
		this.costo = this.costoPromo;
			}

		
}