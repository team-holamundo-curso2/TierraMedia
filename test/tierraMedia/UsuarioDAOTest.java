package tierraMedia;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class UsuarioDAOTest {

	PromocionesDAO pDAO;
	AtraccionesDAO aDAO;
	UsuarioDAO userDAO;
	List<Atracciones> atrLista;
	List<Promociones> promoLista;
	List<Producto> prodLista;
	List<Producto> prodLista2;
	List<Usuario> userLista;

	@Before
	public void setUp() throws Exception {
		aDAO = new AtraccionesDAO();
		pDAO = new PromocionesDAO();
		userDAO = new UsuarioDAO();
		atrLista = new ArrayList<Atracciones>();
		promoLista = new ArrayList<Promociones>();
		userLista = new ArrayList<Usuario>();
		prodLista = new ArrayList<Producto>();
		prodLista2 = new ArrayList<Producto>();
		atrLista = aDAO.crearListaDeAtracciones();
		promoLista.addAll(pDAO.crearListaDePromociones(atrLista));
		prodLista.addAll(promoLista);
		prodLista.addAll(atrLista);
		userLista.addAll(userDAO.crearListaDeUsuarios());
		
		prodLista2.add(promoLista.get(0));
		prodLista2.add(atrLista.get(1));
		
		
	}

	@Test
	public void crearListaUsuariosTest() throws SQLException {
		assertEquals(4, userLista.size());
	}
	
	@Test
	public void filtrarProductosTest() throws SQLException {
		assertEquals(4, userLista.get(0).consultarItinerario(prodLista, userLista.get(0)).size());
		assertEquals(3, userLista.get(1).consultarItinerario(prodLista, userLista.get(1)).size());
		assertEquals(3, userLista.get(2).consultarItinerario(prodLista, userLista.get(2)).size());
		assertEquals(4, userLista.get(3).consultarItinerario(prodLista, userLista.get(3)).size());
		}
	
	}