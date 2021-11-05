package tierraMedia;

public class AXB extends Promociones {
	private double atraccionGratis;

	public AXB(int id, String tipo, String nombre, String promociones, double condicion) {
		super(id, tipo, nombre, promociones);
		this.atraccionGratis = condicion;
			}

	/**
	 * post:Al costo total se le resta el costo de la última atracción de la promo.
	 */
	public void aplicarPromocionAXB() {
		double costoTotal = 0;
		for (int i = 0; i < this.atracciones.size(); i++) {
			if (atracciones.get(i).obtenerID() != this.obtenerAtraccionGratis()) {

				costoTotal += this.atracciones.get(i).obtenerCosto();
			}
		}
		this.costo = costoTotal;
	}

	public double obtenerAtraccionGratis() {
		return this.atraccionGratis;
	}

	@Override
	public void asignarCosto() {
		this.aplicarPromocionAXB();
		}
}