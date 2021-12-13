package services;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import model.Atracciones;
import model.Usuario;
import persistence.AtraccionDAO;
import persistence.UsuarioDAO;
import persistence.commons.AtraccionException;
import persistence.commons.DAOFactory;

public class BuyAttractionService {

	AtraccionDAO attractionDAO = DAOFactory.getAttractionDAO();
	UsuarioDAO userDAO = DAOFactory.getUserDAO();

	public Map<String, String> buy(Integer userId, Integer attractionId) throws AtraccionException, SQLException {
		Map<String, String> errors = new HashMap<String, String>();

		Usuario user = null;
		try {
			user = userDAO.find(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Atracciones attraction = null;
		try {
			attraction = attractionDAO.find(attractionId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (!attraction.hayCupo()) {
			errors.put("attraction", "No hay cupo disponible");
		}
		if (!user.puedePagarlo(attraction)) {
			errors.put("user", "No tienes dinero suficiente");
		}
		if (!user.tieneTiempo(attraction)) {
			errors.put("user", "No tienes tiempo suficiente");
		}

		if (errors.isEmpty()) {
			user.agregarAItinerario(attraction);
			attraction.restarCupo();
               // REVISAR QUE NO RESTE DOBLE			

			attractionDAO.update(attraction);
			userDAO.update(user);
		}

		return errors;

	}

}
