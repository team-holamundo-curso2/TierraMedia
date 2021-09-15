package tierraMedia;

import static org.junit.Assert.*;
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
	Promociones promo1;
	Promociones promo2;
	Promociones promo3;
	Promociones promo4;
	List<Promociones> promoLista = new ArrayList<Promociones>();
	List<Producto> prodLista = new ArrayList<Producto>();
	List<String> esperado = new ArrayList<String>();
	
	@Before
	public void setUp() throws Exception {
		
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
		promo1 = new Porcentuales(TIPO_DE_ATRACCION.AVENTURA, "Pack Aventura", TIPO_DE_PROMOCIONES.PORCENTUALES, 30, 2,
				atrLista);
		promo2 = new AXB(TIPO_DE_ATRACCION.PAISAJE, "Pack Paisaje", TIPO_DE_PROMOCIONES.AXB, 3, atrLista);
		promo3 = new Absolutas(TIPO_DE_ATRACCION.DEGUSTACION, "Pack Degustacion", TIPO_DE_PROMOCIONES.AXB, 500, 2, atrLista);
		promo4 = new Porcentuales(TIPO_DE_ATRACCION.AVENTURA, "Pack Aventura", TIPO_DE_PROMOCIONES.PORCENTUALES, 50, 3,
				atrLista);
		promoLista.add(promo1);
		promoLista.add(promo2);
		prodLista.addAll(promoLista);
	
	}
	
	@Test
	public void pruebaDeCostosYTiempos() {
		esperado.add("Moria");
		esperado.add("Minas Tirith");
		esperado.add("La Comarca");
		esperado.add("Mordor");
		esperado.add("Abismo de Helm");
		esperado.add("Erebor");
		assertEquals(esperado, promo1.obtenerAtracciones(atrLista));
		promo1.aplicarPromocion(30);
		assertEquals(42, promo1.obtenerCosto(), 0.1);
		assertEquals(19, promo1.obtenerTiempo(), 0.1);
	}
	@Test
	public void pruebaDeEsPromocion() {
		assertTrue(promo1.esPromocion()); //TRUE
		assertFalse(atr1.esPromocion()); //TRUE
		
	}
	
}
