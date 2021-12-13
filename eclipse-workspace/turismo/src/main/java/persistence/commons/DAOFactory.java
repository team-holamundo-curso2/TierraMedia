package persistence.commons;

import persistence.AtraccionDAO;
import persistence.UsuarioDAO;
import persistence.impl.AtraccionDAOImpl;
import persistence.impl.UsuarioDAOImpl;

public class DAOFactory {

	public static UsuarioDAO getUserDAO() {
		return new UsuarioDAOImpl();
	}
	
	public static AtraccionDAO getAttractionDAO() {
		return new AtraccionDAOImpl();
	}
}
