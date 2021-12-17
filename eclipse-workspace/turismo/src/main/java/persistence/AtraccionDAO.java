package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import persistence.commons.AtraccionException;
import persistence.commons.ConnectionProvider;
import persistence.commons.GenericDAO;
import persistence.commons.ProductoException;
import model.Atracciones;

public interface AtraccionDAO extends GenericDAO<Atracciones> {
	
	public static List<Atracciones> crearListaDeAtracciones() throws SQLException {
		String sql = "SELECT atraccion.id, atraccion.nombre, atraccion.costo, atraccion.tiempo, atraccion.cupo, tipo_atraccion.nombre FROM atraccion JOIN tipo_atraccion ON atraccion.tipo_id = tipo_atraccion.id";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet resultados = statement.executeQuery();

		List<Atracciones> atracciones = new LinkedList<Atracciones>();
		while (resultados.next()) {
			try {
				atracciones.add(crearAtracciones(resultados));
			} catch (ProductoException mti) {
				System.err.println(mti.getMessage());
			} catch (NumberFormatException error) {
				System.err.println("El formato es incorrecto");
			} catch (AtraccionException aerror) {
				System.err.println(aerror.getMessage());
			}
		}
		return atracciones;
	}

	private static Atracciones crearAtracciones(ResultSet resultados) throws SQLException {
		return new Atracciones(resultados.getInt(1), resultados.getString(6), resultados.getString(2),
				resultados.getDouble(3), resultados.getDouble(4), resultados.getInt(5), resultados.getString(9),resultados.getInt(7));
	}

	public static int actualizarCupo(Atracciones atr) throws SQLException {
		String sql = "UPDATE ATRACCION SET CUPO = ? WHERE NOMBRE = ?";
		Connection conn = ConnectionProvider.getConnection();

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, atr.obtenerCupo());
		statement.setString(2, atr.obtenerNombre());
		int rows = statement.executeUpdate();

		return rows;
	}

	public static int restaurarCupo(Atracciones atr) throws SQLException {
		String sql = "UPDATE ATRACCION SET CUPO = ? WHERE NOMBRE = ?";
		Connection conn = ConnectionProvider.getConnection();

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, atr.obtenerCupo());
		statement.setString(2, atr.obtenerNombre());
		int rows = statement.executeUpdate();

		return rows;
	}

}
