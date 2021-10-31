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
	}

	@Test
	public void crearListaDeAtraccionesTest() throws SQLException {
		aDAO.crearListaDeAtracciones();
	}

}
