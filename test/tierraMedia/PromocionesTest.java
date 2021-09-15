package tierraMedia;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PromocionesTest {
	Usuario pepita;
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
	List<String> esperado = new ArrayList<String>();

	@Before
	public void setUp() throws Exception {

		pepita = new Usuario("Pepita", TIPO_DE_ATRACCION.AVENTURA, 50, 20);
		atr1 = new Atracciones(TIPO_DE_ATRACCION.AVENTURA, "Moria", 10, 2, 6);
		atr2 = new Atracciones(TIPO_DE_ATRACCION.PAISAJE, "Minas Tirith", 5, 2.5, 25);
		atr3 = new Atracciones(TIPO_DE_ATRACCION.DEGUSTACION, "La Comarca", 3, 6.5, 150);
		atr4 = new Atracciones(TIPO_DE_ATRACCION.AVENTURA, "Mordor", 25, 3, 4);
		atr5 = new Atracciones(TIPO_DE_ATRACCION.PAISAJE, "Abismo de Helm", 5, 2, 15);
		atr6 = new Atracciones(TIPO_DE_ATRACCION.PAISAJE, "Erebor", 12, 3, 32);
		atrLista.add(atr1);
		atrLista.add(atr2);
		atrLista.add(atr3);
		atrLista.add(atr4);
		atrLista.add(atr5);
		atrLista.add(atr6);
		atrLista2.add(atr2);
		atrLista2.add(atr5);
		atrLista2.add(atr6);
		promo1 = new Porcentuales(TIPO_DE_ATRACCION.AVENTURA, "Pack Aventura", TIPO_DE_PROMOCIONES.PORCENTUALES, 30, 2,
				atrLista);
		promo2 = new AXB(TIPO_DE_ATRACCION.PAISAJE, "Pack Paisaje", TIPO_DE_PROMOCIONES.AXB, 3, atrLista2);
		promo3 = new Absolutas(TIPO_DE_ATRACCION.DEGUSTACION, "Pack Degustacion", TIPO_DE_PROMOCIONES.AXB, 500, 2,
				atrLista);
		promo4 = new Porcentuales(TIPO_DE_ATRACCION.AVENTURA, "Pack Aventura", TIPO_DE_PROMOCIONES.PORCENTUALES, 50, 3,
				atrLista);
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
		assertEquals(19, promo4.obtenerTiempo(), 0.1);

		// Prueba de calculo de costo y tiempo de Promocion AXB.

		assertEquals(10, promo2.obtenerCosto(), 0.1); // Se descuenta la ultima atraccion recibida
		assertEquals(7.5, promo2.obtenerTiempo(), 0.1); // Se suman los tiempos de todas las atracciones de la lista.

	}

	@Test
	public void pruebaDeCupo() {
		// Resta 1 de cupo de cada atraccion
		promo2.restarCupo();
		assertEquals(24, promo2.atracciones.get(0).obtenerCupoDePersonas());
		assertEquals(14, promo2.atracciones.get(1).obtenerCupoDePersonas());
		assertEquals(31, promo2.atracciones.get(2).obtenerCupoDePersonas());
	}

	@Test
	public void pruebaDeContiene() {
		// Prueba si una atraccion esta dentro de la promocion. 
		assertTrue(promo2.contiene(atr5)); // TRUE
		assertFalse(promo2.contiene(atr1)); // FALSE

	} 

}