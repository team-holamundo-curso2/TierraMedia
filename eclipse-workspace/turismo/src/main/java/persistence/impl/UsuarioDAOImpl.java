package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.Usuario;
import model.nullobjects.NullUser;
import persistence.UsuarioDAO;
import persistence.commons.ConnectionProvider;
import persistence.commons.MissingDataException;
import persistence.commons.UsuarioException;
import model.Atracciones;
import model.Producto;

public class UsuarioDAOImpl implements UsuarioDAO {

	public int insert(Usuario user) {
		try { // nombre, int preferencia, double monedas, double tiempoDisponible, Boolean
				// admin, Boolean borrad
			String sql = "INSERT INTO USUARIO (NOMBRE, PREFERENCIA, MONEDAS, TIEMPO) VALUES (?,?,?,?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, user.obtenerNombre());
			statement.setInt(2, user.obtenerPreferencia());
			statement.setDouble(3, user.obtenerMonedas());
			statement.setDouble(4, user.obtenerTiempo());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int update(Usuario user) {
		try {
			String sql = "UPDATE USUARIO SET MONEDAS = ?, TIEMPO = ? WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setDouble(1, user.obtenerMonedas());
			statement.setDouble(2, user.obtenerTiempo());
			statement.setInt(3, user.obtenerId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int delete(Usuario user) {
		try {
			String sql = "DELETE FROM USUARIO WHERE NOMBRE = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, user.obtenerNombre());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public Usuario findByUsername(String username) {

		try {
			String sql = "SELECT * FROM USUARIO WHERE NOMBRE = ? AND BORRADO=0";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet resultados = statement.executeQuery();

			Usuario user = NullUser.build();

			if (resultados.next()) {
				user = toUser(resultados);
			}

			return user;
		} catch (Exception e) {
			e.printStackTrace();
			throw new MissingDataException(e);
		}
	}

	public Usuario find(Integer id) {
		try {
			String sql = "SELECT * FROM USUARIO WHERE ID = ? AND BORRADO=0";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultados = statement.executeQuery();

			Usuario user = NullUser.build();

			if (resultados.next()) {
				user = toUser(resultados);
			}

			return user;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int countAll() {
		try {
			String sql = "SELECT COUNT(1) AS TOTAL FROM USUARIO";/* ¿WHERE BORRADO=0? */
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

	public List<Usuario> findAll() {
		try {
			String sql = "SELECT * FROM USUARIO WHERE BORRADO=0";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Usuario> usuarios = new LinkedList<Usuario>();
			while (resultados.next()) {
				usuarios.add(toUser(resultados));
			}

			return usuarios;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Usuario toUser(ResultSet userRegister) throws SQLException, UsuarioException {
		return new Usuario(userRegister.getInt(1), userRegister.getString(2), userRegister.getInt(3),
				userRegister.getDouble(4), userRegister.getDouble(5), userRegister.getBoolean(6),
				userRegister.getBoolean(7));
	}

	/*
	 * public List<Usuario> crearListaDeUsuarios() throws SQLException { String sql
	 * =
	 * "SELECT usuario.id, usuario.nombre, tipo_atraccion.nombre, usuario.monedas, usuario.tiempo FROM USUARIO JOIN tipo_atraccion ON tipo_atraccion.id = usuario.preferencia"
	 * ; Connection conn = ConnectionProvider.getConnection(); PreparedStatement
	 * statement = conn.prepareStatement(sql); ResultSet resultados =
	 * statement.executeQuery();
	 * 
	 * List<Usuario> usuarios = new LinkedList<Usuario>(); while (resultados.next())
	 * { try{ usuarios.add(crearUsuario(resultados)); }catch (UsuarioException rne)
	 * { System.err.println(rne.getMessage()); } } return usuarios; }
	 */

	public int consultaPrimerUso(Usuario user) throws SQLException {
		String sql = "SELECT COUNT(*)\n" + "FROM itinerario_usuario\n" + "WHERE usuario_id = ?";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, user.obtenerId());
		ResultSet resultados = statement.executeQuery();
		return resultados.getInt(1);

	}

	public List<Producto> filtrarProductos(List<Atracciones> list, Usuario user) throws SQLException {
		List<Producto> prodFiltrada = new ArrayList<Producto>();
		String sql = "SELECT *\r\n" + "FROM itinerario_usuario\r\n" + "WHERE usuario_id = ?";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, user.obtenerId());
		ResultSet resultado = statement.executeQuery();
		while (resultado.next()) {
			if (resultado.getInt(3) == 0) {
				int idAtraccion = resultado.getInt(4);
				prodFiltrada.add(buscarAtraccion(idAtraccion, list));
			}
		}
		return prodFiltrada;
	}

	private Producto buscarAtraccion(int idAtraccion, List<Atracciones> list) throws SQLException {
		Producto p = null;
		for (Producto produc : list) {
			if (!produc.esPromocion() && produc.obtenerID() == idAtraccion) {
				p = produc;
			}

		}
		return p;
	}

	private Producto buscarPromocion(int idPromo, List<Producto> productos) throws SQLException {
		Producto p = null;
		for (Producto produc : productos) {
			if (produc.esPromocion() && produc.obtenerID() == idPromo) {
				p = produc;
			}

		}
		return p;
	}

	public int actualizarItinerario(Usuario user) throws SQLException {
		int rowss = 0;
		String sql = "INSERT INTO itinerario_usuario (usuario_id, promo_id) VALUES (?, ?)";
		String sql2 = "INSERT INTO itinerario_usuario (usuario_id, atraccion_id) VALUES (?, ?)";

		for (int i = user.obtenerCantidadProductosPreCargados(); i < user.obtenerItinerario().size(); i++) {

			if (user.obtenerItinerario().get(i).esPromocion()) {

				Connection conn = ConnectionProvider.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setInt(2, user.obtenerItinerario().get(i).obtenerID());
				statement.setInt(1, user.obtenerId());
				int rows = statement.executeUpdate();
				rowss = rows;
			} else {

				Connection conn = ConnectionProvider.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql2);
				statement.setInt(2, user.obtenerItinerario().get(i).obtenerID());
				statement.setInt(1, user.obtenerId());
				int rows = statement.executeUpdate();
				rowss = rows;
			}

		}
		return rowss;

	}
}
