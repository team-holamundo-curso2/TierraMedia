package tierraMedia;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PromocionesDAOTest {
	PromocionesDAO pDAO;
	AtraccionesDAO aDAO;
	List<Atracciones> atrLista;
	List<Promociones> promoLista;

	@Before
	public void setUp() throws Exception {
		aDAO = new AtraccionesDAO();
		pDAO = new PromocionesDAO();
		atrLista = new ArrayList<Atracciones>();
		promoLista = new ArrayList<Promociones>();
		atrLista = aDAO.crearListaDeAtracciones();
		promoLista.addAll(pDAO.crearListaDePromociones(atrLista));
		
		
	}

	@Test
	public void crearListaDePromocionesTest() throws SQLException {
		assertEquals(4, promoLista.size());
	}
	
	@Test
	public void consultarAtraccionesTest() {
		assertEquals(2, promoLista.get(0).obtenerAtracciones().size());
		assertEquals(3, promoLista.get(1).obtenerAtracciones().size());
		assertEquals(2, promoLista.get(2).obtenerAtracciones().size());
		assertEquals(3, promoLista.get(3).obtenerAtracciones().size());
			}

	
}