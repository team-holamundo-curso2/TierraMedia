package tierraMedia;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PromocionesTest {

	Atracciones atr1;
	Atracciones atr2;
	Atracciones atr3;
	Atracciones atr4;
	Atracciones atr5;
	Atracciones atr6;
	List<Atracciones> atrLista = new ArrayList<Atracciones>();
	List<Atracciones> atrLista2 = new ArrayList<Atracciones>();
	Promociones promo1;
	Promociones promo2;
	Promociones promo3;
	Promociones promo4;
	List<Promociones> promoLista = new ArrayList<Promociones>();
	List<Producto> prodLista = new ArrayList<Producto>();

	@Before
	public void setUp() throws Exception {
		atr1 = new Atracciones(1, "AVENTURA", "Moria", 10, 2, 6);
		atr2 = new Atracciones(2, "PAISAJE", "Minas Tirith", 5, 2.5, 25);
		atr3 = new Atracciones(3, "DEGUSTACION", "La Comarca", 3, 6.5, 150);
		atr4 = new Atracciones(4, "AVENTURA", "Mordor", 25, 3, 4);
		atr5 = new Atracciones(5, "PAISAJE", "Abismo de Helm", 5, 2, 15);
		atr6 = new Atracciones(7, "PAISAJE", "Erebor", 12, 3, 32);
		atrLista.add(atr1);
		atrLista.add(atr2);
		atrLista.add(atr3);
		atrLista.add(atr4);
		atrLista.add(atr5);
		atrLista.add(atr6);
		atrLista2.add(atr2);
		atrLista2.add(atr5);
		atrLista2.add(atr6);
		promo2 = new AXB(3, "PAISAJE", "Pack Paisaje", "AXB", 3);
		promo4 = new Porcentuales(1, "AVENTURA", "Pack Aventura", "PORCENTUALES", 50);
		promoLista.add(promo1);
		promoLista.add(promo2);
		prodLista.addAll(promoLista);

	}

	@Test
	public void pruebaDeCostosYTiempos() {

		// Prueba de calculo de costo y tiempo de Promocion Porcentual
		// (El constructor invoca el metodo "aplicarPromocion()" para generar su costo y
		// tiempo.

		assertEquals(30, promo4.obtenerCosto(), 0.1); // Descuento al 50%
		assertEquals(19, promo4.obtenerTiempo(), 0.1); // Se suman los tiempos de todas las atracciones de la lista.

		// Prueba de calculo de costo y tiempo de Promocion AXB.

		assertEquals(10, promo2.obtenerCosto(), 0.1); // Se descuenta la ultima atraccion recibida
		assertEquals(7.5, promo2.obtenerTiempo(), 0.1); // Se suman los tiempos de todas las atracciones de la lista.

	}

	@Test
	public void pruebaDeCupo() throws SQLException {
		// Resta 1 de cupo de cada atraccion
		promo2.restarCupo();
		assertEquals(24, promo2.atracciones.get(0).obtenerCupoDePersonas());
		assertEquals(14, promo2.atracciones.get(1).obtenerCupoDePersonas());
		assertEquals(31, promo2.atracciones.get(2).obtenerCupoDePersonas());
	}

	@Test
	public void pruebaDeContiene() {
		// Prueba si una atraccion esta dentro de la promocion o no.
		assertTrue(promo2.contiene(atr5)); // TRUE
		assertFalse(promo2.contiene(atr1)); // FALSE

	}

}