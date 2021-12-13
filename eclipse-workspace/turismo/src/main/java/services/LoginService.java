package services;

import model.Usuario;
import model.nullobjects.NullUser;
import persistence.UsuarioDAO;
import persistence.commons.DAOFactory;
import persistence.commons.UsuarioException;

public class LoginService {

	public Usuario login(String nombre) throws UsuarioException {
		UsuarioDAO userDao = DAOFactory.getUserDAO();
    	Usuario user = userDao.findByUsername(nombre);
    	
    	if (user.isNull()) {
    		user = NullUser.build();
    	}
    	return user;
	}
	
}
