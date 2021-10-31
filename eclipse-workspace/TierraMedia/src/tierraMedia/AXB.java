package tierraMedia;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
public static void main(String[] args) throws SQLException {
	List<Atracciones> atr = new ArrayList<Atracciones>();
	AtraccionesDAO atrDAO = new AtraccionesDAO();
	atr.addAll(atrDAO.crearListaDeAtracciones());
	AXB promoAXB = new AXB(3, "PAISAJE", "Pack Paisaje", "AXB", 7);
	promoAXB.asignarListaAtracciones(atr);
	
	
	double costoTotal = 0;
	for (int i = 0; i < promoAXB.atracciones.size(); i++) {
		System.out.println(promoAXB.atracciones.get(i).obtenerID());
		System.out.println(promoAXB.obtenerAtraccionGratis());
		if (promoAXB.atracciones.get(i).obtenerID() != promoAXB.obtenerAtraccionGratis()) {

			costoTotal += promoAXB.atracciones.get(i).obtenerCosto();
			System.out.println(costoTotal);
		}
	}

	
	
	
	
}
}