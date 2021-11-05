package tierraMedia;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class AtraccionesDAOTest {
	AtraccionesDAO aDAO;
	List<Atracciones> atrLista;

	@Before
	public void setUp() throws Exception {
		aDAO = new AtraccionesDAO();
		atrLista = new ArrayList<Atracciones>();
		atrLista.addAll(aDAO.crearListaDeAtracciones());
	}

	@Test 
	public void crearListaDeAtraccionesTest() throws SQLException {
		assertEquals(8, atrLista.size());
	}
	
	@Test
	public void cupoTest() throws SQLException {
		atrLista.get(0).obtenerCupoDePersonas();
		assertEquals(48, atrLista.get(0).obtenerCupoDePersonas());
		atrLista.get(0).restarCupo();
		assertEquals(47, atrLista.get(0).obtenerCupoDePersonas());
		atrLista.get(0).reiniciarCupo();
		assertEquals(48, atrLista.get(0).obtenerCupoDePersonas());
		}
}