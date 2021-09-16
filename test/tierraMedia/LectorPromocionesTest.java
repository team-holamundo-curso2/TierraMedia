package tierraMedia;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class LectorPromocionesTest {

	String rutaP = "archivos/promocionesPrueba.txt";
	String rutaP2 = "archivos/promociones.txt";
	String rutaA = "archivos/atracciones.txt";
	List<Promociones> promos;
	List<Atracciones> atracciones;
	LectorAtracciones lec = new LectorAtracciones();
	LectorPromociones lector = new LectorPromociones();

	@Before
	public void setUp() throws Exception {
		atracciones = lec.leerAtracciones(rutaA);
	}

	@Test
	public void pruebaDeCreacionDePromocionesArchivoConErrores() {
		// Se pasan por archivo 4 promociones de las cuales 2 tienen errores.
		promos = lector.leerPromociones(atracciones, rutaP);
		assertEquals(2, promos.size());
	}

	@Test
	public void pruebaDeCreacionDePromociones() {
		promos = lector.leerPromociones(atracciones, rutaP2);
		assertEquals(4, promos.size());
	}
}
