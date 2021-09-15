package tierraMedia;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TurismoTierraMediaTest {

	TurismoTierraMedia appDePrueba;
	TurismoTierraMedia appDePrueba2;
	String rutaA = "archivos/atracciones.txt";
	String rutaP = "archivos/promociones.txt";
	String rutaU = "archivos/usuario.txt";
	String rutaAP = "archivos/atraccionesPrueba.txt";
	String rutaPP = "archivos/promocionesPrueba.txt";
	String rutaUP = "archivos/usuarioPrueba.txt";

	// ARRANGE
	Ofertable ofertador;
	Usuario pepita;
	Usuario norita;
	Atracciones atr1;
	Atracciones atr2;
	Atracciones atr3;
	Atracciones atr4;
	Atracciones atr5;
	Atracciones atr6;
	Atracciones atr7;
	Atracciones atr8;
	Atracciones atr9;

	List<Atracciones> atrLista = new ArrayList<Atracciones>();
	List<Atracciones> atrLista2 = new ArrayList<Atracciones>();

	Promociones promo1;
	Promociones promo2;

	List<Promociones> promoLista = new ArrayList<Promociones>();
	List<Producto> prodLista = new ArrayList<Producto>();
	List<Producto> esperado = new ArrayList<Producto>();

	@Before
	public void setUp() {

		pepita = new Usuario("Pepita", TIPO_DE_ATRACCION.AVENTURA, 50, 20);
		norita = new Usuario("Norita", TIPO_DE_ATRACCION.PAISAJE, 30, 10);
		atr1 = new Atracciones(TIPO_DE_ATRACCION.AVENTURA, "Moria", 10, 2, 6);
		atr2 = new Atracciones(TIPO_DE_ATRACCION.PAISAJE, "Minas Tirith", 5, 2.5, 25);
		atr3 = new Atracciones(TIPO_DE_ATRACCION.DEGUSTACION, "La Comarca", 3, 6.5, 150);
		atr4 = new Atracciones(TIPO_DE_ATRACCION.AVENTURA, "Mordor", 25, 3, 4);
		atr5 = new Atracciones(TIPO_DE_ATRACCION.PAISAJE, "Abismo de Helm", 5, 2, 15);
		atr6 = new Atracciones(TIPO_DE_ATRACCION.PAISAJE, "Erebor", 12, 3, 32);
		atr7 = new Atracciones(TIPO_DE_ATRACCION.PAISAJE, "Erebor2", 122, 3, 32);
		atr8 = new Atracciones(TIPO_DE_ATRACCION.PAISAJE, "Erebor3", 123, 3, 32);
		atr9 = new Atracciones(TIPO_DE_ATRACCION.PAISAJE, "Erebor4", 124, 3, 32);
		atrLista.add(atr1);
		atrLista.add(atr2);
		atrLista.add(atr3);
		atrLista.add(atr4);
		atrLista.add(atr5);
		atrLista.add(atr6);
		atrLista2.add(atr7);
		atrLista2.add(atr8);
		atrLista2.add(atr9);
		promo1 = new Porcentuales(TIPO_DE_ATRACCION.AVENTURA, "Pack Aventura", TIPO_DE_PROMOCIONES.PORCENTUALES, 30, 2,
				atrLista);
			

		prodLista.add(promo1);

		prodLista.add(atr1);
		prodLista.add(atr2);
		prodLista.add(atr3);
		
		esperado.add(promo1);
		esperado.add(atr1);
		esperado.add(atr2);
		esperado.add(atr3);
		
		pepita.itinerario = prodLista;

		ofertador = new Ofertable(pepita, prodLista);
		appDePrueba = new TurismoTierraMedia(rutaUP, rutaAP, rutaPP);
		appDePrueba2 = new TurismoTierraMedia(rutaU, rutaA, rutaP);

	}

	@Test
	public void testDeOrdenarProductos() {
		appDePrueba2.crearListas();
		List<Producto>actual = appDePrueba2.ordenarProductos(pepita);
		System.out.println(esperado);
		assertEquals(esperado, actual);
		
		//asserArrayEquals(esperado, actual);

	}

}