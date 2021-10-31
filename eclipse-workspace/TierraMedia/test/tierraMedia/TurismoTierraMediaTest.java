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

		pepita = new Usuario(1, "Pepita", "AVENTURA", 50, 20);
		atr2 = new Atracciones(2, "PAISAJE", "Minas Tirith", 5, 2.5, 25);
		atr4 = new Atracciones(4, "AVENTURA", "Mordor", 25, 3, 4);
		atr6 = new Atracciones(8, "AVENTURA", "Bosque Negro", 3, 4, 12);
		atrLista.add(atr4);
		atrLista.add(atr6);
		promo1 = new Porcentuales(1,"AVENTURA", "Pack Aventura", "PORCENTUALES", 30);
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
		appDePrueba2 = new TurismoTierraMedia();
	}

	@Test
	public void testDeOrdenarProductos() {
		appDePrueba2.setProductosDesordenados(prodLista);
		List<Producto> actual = appDePrueba2.ordenarProductos(pepita);
		assertEquals(esperado, actual);
	}

}
