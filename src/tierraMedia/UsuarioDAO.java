package tierraMedia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class UsuarioDAO {

	public List<Usuario> crearListaDeUsuarios() throws SQLException {
		String sql = "SELECT usuario.id, usuario.nombre, tipo_atraccion.nombre, usuario.monedas, usuario.tiempo FROM USUARIO JOIN tipo_atraccion ON tipo_atraccion.id = usuario.preferencia";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet resultados = statement.executeQuery();

		List<Usuario> usuarios = new LinkedList<Usuario>();
		while (resultados.next()) {
			try{
				usuarios.add(crearUsuario(resultados));
			}catch (UsuarioException rne) {
				System.err.println(rne.getMessage());
			}
		}
		return usuarios;
	}

	int consultaPrimerUso(Usuario user) throws SQLException {
		String sql = "SELECT COUNT(*)\n" + "FROM itinerario_usuario\n" + "WHERE usuario_id = ?";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, user.obtenerIdUsuario());
		ResultSet resultados = statement.executeQuery();
		return resultados.getInt(1);

	}

	List<Producto> filtrarProductos(List<Producto> productos, Usuario user) throws SQLException {
		List<Producto> prodFiltrada = new ArrayList<Producto>();
		String sql = "SELECT *\r\n" + "FROM itinerario_usuario\r\n" + "WHERE usuario_id = ?";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, user.obtenerIdUsuario());
		ResultSet resultado = statement.executeQuery();
		while (resultado.next()) {
			if (resultado.getInt(3) == 0) {
				int idAtraccion = resultado.getInt(4);
				prodFiltrada.add(buscarAtraccion(idAtraccion, productos));
			} else {
				int idPromo = resultado.getInt(3);
				prodFiltrada.add(buscarPromocion(idPromo, productos));
			}
		}
		return prodFiltrada;
	}

	private Producto buscarAtraccion(int idAtraccion, List<Producto> productos) throws SQLException {
		Producto p = null;
		for (Producto produc : productos) {
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

	private Usuario crearUsuario(ResultSet resultados) throws SQLException {
		return new Usuario(resultados.getInt(1), resultados.getString(2), resultados.getString(3), resultados.getInt(4),
				resultados.getInt(5));

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
				statement.setInt(1, user.obtenerIdUsuario());
				int rows = statement.executeUpdate();
				rowss = rows;
			} else {

				Connection conn = ConnectionProvider.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql2);
				statement.setInt(2, user.obtenerItinerario().get(i).obtenerID());
				statement.setInt(1, user.obtenerIdUsuario());
				int rows = statement.executeUpdate();
				rowss = rows;
			}

		}
		return rowss;

	}

	public int actualizarMonedasYTiempo(Usuario user) throws SQLException {
		String sql = "UPDATE USUARIO SET MONEDAS = ?, TIEMPO = ? WHERE NOMBRE = ?";
		Connection conn = ConnectionProvider.getConnection();

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setDouble(1, user.obtenerMonedas());
		statement.setDouble(2, user.obtenerTiempoDisponible());
		statement.setString(3, user.obtenerNombre());
		int rows = statement.executeUpdate();

		return rows;
	}

}