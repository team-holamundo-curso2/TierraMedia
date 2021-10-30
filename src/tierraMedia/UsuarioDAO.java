package tierraMedia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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

	public void actualizarItinerario(Usuario user) throws SQLException {
		
		if (user.obtenerItinerario().size() > 0) {
			for (int i = user.obtenerCantidadProductosAceptados() + 1; i <= user.obtenerItinerario().size(); i++) {
				String sql = "UPDATE ITINERARIO_USUARIO SET PROMO_NOMBRE = ? AND ATRACCION_NOMBRE = ? WHERE USUARIO_ID = ?";
				Connection conn = ConnectionProvider.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql);
				if (user.obtenerItinerario().get(i).esPromocion()) {
				statement.setString(1, user.obtenerItinerario().get(i).obtenerNombre());
				} else {
					statement.setString(2, user.obtenerItinerario().get(i).obtenerNombre());
				}				
				statement.setInt(3, user.obtenerIdUsuario());
				int rows = statement.executeUpdate();
			}
		} else {
			String sql = "INSERT INTO itinerario_usuario (usuario_id, promo_nombre, atraccion_nombre) VALUES"
					+ " ('?', '?', '?' )";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, user.obtenerIdUsuario());
			for (int i = 0; i < user.obtenerItinerario().size(); i++) {
				if (user.obtenerItinerario().get(i).esPromocion()) {
				statement.setString(2, user.obtenerItinerario().get(i).obtenerNombre());
			} else {
				statement.setString(3, user.obtenerItinerario().get(i).obtenerNombre());
			}
			}
			int rows = statement.executeUpdate();
		}			
	}

	public int actualizarMonedas(Usuario user) throws SQLException {
		String sql = "UPDATE USUARIO SET MONEDAS = ? WHERE NOMBRE = ?";
		Connection conn = ConnectionProvider.getConnection();

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setDouble(1, user.obtenerMonedas());
		statement.setString(2, user.obtenerNombre());
		int rows = statement.executeUpdate();

		return rows;
	}

	public int actualizarTiempo(Usuario user) throws SQLException {
		String sql = "UPDATE USUARIO SET TIEMPO = ? WHERE NOMBRE = ?";
		Connection conn = ConnectionProvider.getConnection();

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setDouble(1, user.obtenerTiempoDisponible());
		statement.setString(2, user.obtenerNombre());
		int rows = statement.executeUpdate();

		return rows;
	}

	public static void main(String[] args) throws SQLException {

		UsuarioDAO userDAO = new UsuarioDAO();
		List<Usuario> userLista = new ArrayList<Usuario>();
		userLista.addAll(userDAO.crearListaDeUsuarios());

		AtraccionesDAO aDAO = new AtraccionesDAO();
		List<Atracciones> atrLista = new ArrayList<Atracciones>();
		atrLista.addAll(aDAO.crearListaDeAtracciones());
		System.out.println(atrLista.get(1));

		System.out.println(userLista.get(1).obtenerMonedas());
		System.out.println(userLista.get(1).obtenerTiempoDisponible());
		System.out.println("_____________________________");

		userLista.get(1).aceptar(atrLista.get(1));
		System.out.println(userLista.get(1).obtenerMonedas());
		System.out.println(userLista.get(1).obtenerTiempoDisponible());
		System.out.println("_____________________________");
		System.out.println(userLista);
		System.out.println(userLista.get(2).obtenerIdUsuario());
	}
}
