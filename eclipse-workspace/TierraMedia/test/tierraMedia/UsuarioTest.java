package tierraMedia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class UsuarioTest {
	Usuario pepita;
	Atracciones atr1;
	Atracciones atr2;
	Atracciones atr3;
	Atracciones atr4;
	Atracciones atr5;
	Atracciones atr6;
	List<Atracciones> atrLista = new ArrayList<Atracciones>();
	Promociones promo1;
	Promociones promo2;
	List<Promociones> promoLista = new ArrayList<Promociones>();
	List<Producto> prodLista = new ArrayList<Producto>();
	List<String> esperado = new ArrayList<String>();

	@Before
	public void setUp() {
		pepita = new Usuario(1, "Pepita", "AVENTURA", 50, 20);
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
		prodLista.add(atr1);
		prodLista.add(atr2);
		prodLista.add(atr3);

		// Crea Itinerario de Pepita
		pepita.cargarItinerario(prodLista);
	}

	@Test
	public void pruebaDeAceptarAtracciones() throws AtraccionException, SQLException {
		// pepita se construye con 50 monedas y 20 de tiempo.
		assertTrue(pepita.aceptar(atr1));
		assertEquals(40, pepita.obtenerMonedas(), 0.1);
		assertEquals(18, pepita.obtenerTiempoDisponible(), 0.1);
	}

	@Test
	public void pruebaResumenItinerario() {
		String esperada = "Costo Total =18.0, Tiempo Total =11.0";
		assertEquals(esperada, pepita.resumenItinerario());
	}

}
