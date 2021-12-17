package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.Atracciones;
import persistence.AtraccionDAO;
import persistence.commons.ConnectionProvider;
import persistence.commons.MissingDataException;

public class AtraccionDAOImpl implements AtraccionDAO {

	/* NO RECONOCE LA CLASE ATRACCIONES */

	public List<Atracciones> findAll() {
		try {
			String sql = "SELECT * FROM ATRACCION WHERE BORRADO = 0";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Atracciones> attractions = new LinkedList<Atracciones>();
			while (resultados.next()) {
				attractions.add(toAttraction(resultados));
			}

			return attractions;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public Atracciones find(Integer id) {
		try {
			String sql = "SELECT * FROM ATRACCION WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);

			ResultSet resultados = statement.executeQuery();

			Atracciones attraction = null;
			if (resultados.next()) {
				attraction = toAttraction(resultados);
			}
			System.out.println(attraction);
			return attraction;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	/*
	 * REVISAR QUE LOS GET COINCIDAN CON EL ORDEN DE LOS CONSTRUCTORES Y LA TABLA DE
	 * BD
	 */
	private Atracciones toAttraction(ResultSet attractionRegister) throws SQLException {
		return new Atracciones(attractionRegister.getInt(1), attractionRegister.getString(6),
				attractionRegister.getString(2), attractionRegister.getDouble(5), attractionRegister.getDouble(3),
				attractionRegister.getInt(4), attractionRegister.getString(9), attractionRegister.getInt(7));
	}// Atracciones(Integer id, String tipo, String nombre, Double costo, Double
		// duration, Integer capacity, String descripcion, Integer borrado)

	@Override
	public int insert(Atracciones attraction) {
		try {
			String sql = "INSERT INTO ATRACCION (NOMBRE, COSTO, TIEMPO, CUPO, DESCRIPCION, TIPO_ID) VALUES (?, ?, ?, ?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			int i = 1;
			statement.setString(i++, attraction.obtenerNombre());
			statement.setDouble(i++, attraction.obtenerCosto());
			statement.setDouble(i++, attraction.obtenerTiempo());
			statement.setInt(i++, attraction.obtenerCupo());
			statement.setString(i++, attraction.obtenerDescripcion());
			statement.setInt(i++, attraction.establecerTipo());

			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int update(Atracciones attraction) {
		try {
			String sql = "UPDATE ATRACCION SET NOMBRE = ?, COSTO = ?, TIEMPO = ?, CUPO = ?, DESCRIPCION = ? WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			int i = 1;
			statement.setString(i++, attraction.obtenerNombre());
			statement.setDouble(i++, attraction.obtenerCosto());
			statement.setDouble(i++, attraction.obtenerTiempo());
			statement.setInt(i++, attraction.obtenerCupo());
			statement.setString(i++, attraction.obtenerDescripcion());
			statement.setInt(i++, attraction.obtenerId());
			
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int delete(Atracciones attraction) {
		try {
			String sql = "UPDATE ATRACCION SET BORRADO = 1 WHERE ID = ? ";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, attraction.obtenerID());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int countAll() {
		try {
			String sql = "SELECT COUNT(1) AS TOTAL FROM ATRACCION";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			resultados.next();
			int total = resultados.getInt("TOTAL");

			return total;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int actualizarCupo(Atracciones atr) throws SQLException {
		String sql = "UPDATE ATRACCION SET CUPO = ? WHERE NOMBRE = ?";
		Connection conn = ConnectionProvider.getConnection();

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, atr.obtenerCupo());
		statement.setString(2, atr.obtenerNombre());
		int rows = statement.executeUpdate();

		return rows;
	}

	public int restaurarCupo(Atracciones atr) throws SQLException {
		String sql = "UPDATE ATRACCION SET CUPO = ? WHERE NOMBRE = ?";
		Connection conn = ConnectionProvider.getConnection();

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, atr.obtenerCupo());
		statement.setString(2, atr.obtenerNombre());
		int rows = statement.executeUpdate();

		return rows;
	}

}
