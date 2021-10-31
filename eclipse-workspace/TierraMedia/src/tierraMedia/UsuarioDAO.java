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
			usuarios.add(crearUsuario(resultados));
		}
		return usuarios;
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
