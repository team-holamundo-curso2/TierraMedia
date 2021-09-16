package tierraMedia;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TurismoTierraMediaTest {

	TurismoTierraMedia appDePrueba2;
	Usuario pepita;
	Atracciones atr2;
	Atracciones atr4;
	Atracciones atr6;
	List<Atracciones> atrLista = new ArrayList<Atracciones>();
	Promociones promo1;
	List<Producto> prodLista = new ArrayList<Producto>();
	List<Producto> esperado = new ArrayList<Producto>();

	@Before
	public void setUp() {

		pepita = new Usuario("Pepita", TIPO_DE_ATRACCION.AVENTURA, 50, 20);
		atr2 = new Atracciones(TIPO_DE_ATRACCION.PAISAJE, "Minas Tirith", 5, 2.5, 25);
		atr4 = new Atracciones(TIPO_DE_ATRACCION.AVENTURA, "Mordor", 25, 3, 4);
		atr6 = new Atracciones(TIPO_DE_ATRACCION.AVENTURA, "Bosque Negro", 3, 4, 12);
		atrLista.add(atr4);
		atrLista.add(atr6);
		promo1 = new Porcentuales(TIPO_DE_ATRACCION.AVENTURA, "Pack Aventura", TIPO_DE_PROMOCIONES.PORCENTUALES, 30, 2,
				atrLista);
		// Armado de lista desordenada.
		prodLista.add(atr4);
		prodLista.add(atr2);
		prodLista.add(atr6);
		prodLista.add(promo1);

		// Armado de lista ordenada esperada.
		esperado.add(promo1);
		esperado.add(atr4);
		esperado.add(atr6);
		esperado.add(atr2);
		appDePrueba2 = new TurismoTierraMedia(null, null, null);
	}

	@Test
	public void testDeOrdenarProductos() {
		appDePrueba2.productosDesordenados = prodLista;
		List<Producto> actual = appDePrueba2.ordenarProductos(pepita);
		assertEquals(esperado, actual);
	}

}
