package services;

import java.util.List;
import model.Atracciones;
import persistence.AtraccionDAO;
import persistence.commons.DAOFactory;

public class AttractionService {

	public List<Atracciones> list() {
		return DAOFactory.getAttractionDAO().findAll();
	}
/* REVISAR LA CREACION DE ATRACCIONES*/
	public Atracciones create(Integer id, String tipo, String nombre, Double costo, Double duration, Integer capacity ) {

		Atracciones attraction = new Atracciones(-1, tipo, nombre, costo, duration, capacity);

		if (attraction.esValido()) {
			AtraccionDAO attractionDAO = DAOFactory.getAttractionDAO();
			attractionDAO.insert(attraction);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return attraction;
	}

	public Atracciones update(Integer id, String name, Integer cost, Double duration, Integer capacity) {

		AtraccionDAO attractionDAO = DAOFactory.getAttractionDAO();
		Atracciones attraction = attractionDAO.find(id);

		attraction.establecerNombre(name);
		attraction.establecerCosto(cost); 
		attraction.establecerTiempo(duration);
		attraction.obtenerCupoDePersonas(capacity);

		if (attraction.esValido()) {
			attractionDAO.update(attraction);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return attraction;
	}

	public void delete(Integer id) { /*REHACER CON EL CAMPO BORRADO*/
		Atracciones attraction = new Atracciones(id, null, null, null, null,null);

		AtraccionDAO attractionDAO = DAOFactory.getAttractionDAO();
		attractionDAO.delete(attraction);
	}

	public Atracciones find(Integer id) {
		AtraccionDAO attractionDAO = DAOFactory.getAttractionDAO();
		return attractionDAO.find(id);
	}

}
