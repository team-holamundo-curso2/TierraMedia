package services;

import java.util.List;

import model.Usuario;
import persistence.commons.DAOFactory;
import persistence.commons.UsuarioException;

public class UserService {
	
	public List<Usuario> list() {
		return DAOFactory.getUserDAO().findAll();
	}

	public Usuario create(int id, String nombre, int preferencia, double monedas, double tiempoDisponible, Boolean admin, Boolean borrado) throws UsuarioException {
		Usuario user = new Usuario(-1, nombre, preferencia, monedas, tiempoDisponible, false, false);
		
		if (user.esValido()) {
			DAOFactory.getUserDAO().insert(user);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return user;
	}

	public Usuario find(int id) {
		return DAOFactory.getUserDAO().find(id);
	}

	public Usuario update(int id, String nombre, int preferencia, double monedas, double tiempoDisponible, Boolean admin, Boolean borrado) throws UsuarioException {
		Usuario user = new Usuario(id,  nombre, preferencia, monedas, tiempoDisponible, false, false);
		
		if (user.esValido()) {
			DAOFactory.getUserDAO().update(user);
		}
		return user;
	}

	public void delete(int id) {
		Usuario user=DAOFactory.getUserDAO().find(id);
		DAOFactory.getUserDAO().delete(user);
	}

}
