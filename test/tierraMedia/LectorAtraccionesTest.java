package tierraMedia;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class LectorAtraccionesTest {
	String rutaA = "archivos/atracciones.txt";
	String rutaA2 = "archivos/atraccionesPrueba.txt";
	List<Atracciones> atracciones;
	LectorAtracciones lec = new LectorAtracciones();

	@Before
	public void setUp() throws Exception {
		atracciones = lec.leerAtracciones(rutaA);
	}

	@Test
	public void pruebaDeCreacionDeAtraccionesArchivoConErrores() {
		// Se pasan por archivo 4 atracciones de las cuales 2 tienen errores.
		atracciones = lec.leerAtracciones(rutaA2);
		assertEquals(2, atracciones.size());
	}

	@Test
	public void pruebaDeCreacionDeAtracciones() {
		atracciones = lec.leerAtracciones(rutaA);
		assertEquals(8, atracciones.size());
	}

}
