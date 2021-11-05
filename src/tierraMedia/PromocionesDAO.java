package tierraMedia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PromocionesDAO {

	public List<Promociones> crearListaDePromociones(List<Atracciones> atraccionesD) throws SQLException {
		List<Promociones> promociones = new ArrayList<Promociones>();
		Map<Promociones, List<Atracciones>> mapPromo = new HashMap<Promociones, List<Atracciones>>();

		String sql = consultaDePromo();
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet resultados = statement.executeQuery();

		while (resultados.next()) {
			try {
			Promociones promo = crearPromociones(resultados.getInt(1), resultados.getString(4), resultados.getString(2),
					resultados.getString(3), resultados.getDouble(5));

			if (mapPromo.containsKey(promo)) {
				List<Atracciones> atr = mapPromo.get(promo);
				atr.addAll(consultarAtracciones(promo, atraccionesD));
				mapPromo.put(promo, atr);
			} else {
				List<Atracciones> atr = new ArrayList<Atracciones>();
				atr.addAll(consultarAtracciones(promo, atraccionesD));
				mapPromo.put(promo, atr);
			}
			} catch (ProductoException mti) {
				System.err.println(mti.getMessage());
			} catch (NumberFormatException error) {
				System.err.println("El formato es incorrecto");
			} catch (PromocionesException promerr) {
				System.err.println(promerr.getMessage());
			}
			
		}
		for (Map.Entry<Promociones, List<Atracciones>> entry : mapPromo.entrySet()) {
			entry.getKey().asignarListaAtracciones(entry.getValue());
			entry.getKey().asignarCosto(); // APLICAR COSTO SEGUN PROMO
			promociones.add(entry.getKey());
		}
		return promociones;
	}

	private List<Atracciones> consultarAtracciones(Promociones promo, List<Atracciones> atraccionesD)
			throws SQLException {
		List<Atracciones> atrFiltrada = new ArrayList<Atracciones>();
		String sql = "SELECT atraccion_id\r\n" + "FROM atraccion_promocion\r\n" + "WHERE promocion_id = ?";
		Connection conn = ConnectionProvider.getConnection();

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, promo.obtenerID());
		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {
			for (Atracciones atraccion : atraccionesD) {
				if (resultado.getInt(1) == atraccion.obtenerID()) {
					atrFiltrada.add(atraccion);
				}
			}
		}
		return atrFiltrada;

	}

	private String consultaDePromo() {
		String consulta = "SELECT promocion.id, promocion.nombre, tipo_promo.nombre AS 'Promocion', tipo_atraccion.nombre AS 'Tipo Atraccion', porcentuales.descuento AS 'Condicion'\r\n"
				+ "FROM promocion\r\n"
				+ "JOIN porcentuales ON promocion.tipo_promo_id = 3 AND promocion.id = porcentuales.promo_id\r\n"
				+ "JOIN atraccion_promocion ON promocion.id = atraccion_promocion.promocion_id\r\n"
				+ "JOIN atraccion ON atraccion_promocion.atraccion_id = atraccion.id \r\n"
				+ "JOIN tipo_promo ON promocion.tipo_promo_id = tipo_promo.id\r\n"
				+ "JOIN tipo_atraccion ON porcentuales.tipo_atraccion = tipo_atraccion.id\r\n"
				+ "GROUP BY promocion.nombre\r\n" + "\r\n" + "UNION ALL\r\n" + "\r\n" + "\r\n"
				+ "SELECT promocion.id, promocion.nombre, tipo_promo.nombre AS 'Promocion',tipo_atraccion.nombre AS 'Tipo Atraccion', absoluta.total AS 'Condicion'\r\n"
				+ "FROM promocion\r\n"
				+ "JOIN absoluta ON promocion.tipo_promo_id = 1 AND promocion.id = absoluta.promo_id\r\n"
				+ "JOIN atraccion_promocion ON promocion.id = atraccion_promocion.promocion_id\r\n"
				+ "JOIN atraccion ON atraccion_promocion.atraccion_id = atraccion.id \r\n"
				+ "JOIN tipo_promo ON promocion.tipo_promo_id = tipo_promo.id\r\n"
				+ "JOIN tipo_atraccion ON absoluta.tipo_atraccion = tipo_atraccion.id\r\n"
				+ "GROUP BY promocion.nombre\r\n" + "UNION ALL\r\n" + "\r\n"
				+ "SELECT promocion.id, promocion.nombre, tipo_promo.nombre AS 'Promocion',tipo_atraccion.nombre AS 'Tipo Atraccion', axb.atraccion_gratis_id AS 'Condicion'\r\n"
				+ "FROM promocion\r\n" + "JOIN axb ON promocion.tipo_promo_id = 2 AND promocion.id = axb.promo_id\r\n"
				+ "JOIN atraccion_promocion ON promocion.id = atraccion_promocion.promocion_id\r\n"
				+ "JOIN atraccion ON atraccion_promocion.atraccion_id = atraccion.id\r\n"
				+ "JOIN tipo_promo ON promocion.tipo_promo_id = tipo_promo.id\r\n"
				+ "JOIN tipo_atraccion ON axb.tipo_atraccion = tipo_atraccion.id\r\n" + "GROUP BY promocion.nombre";
		return consulta;
	}

	private Promociones crearPromociones(int id, String tipo, String nombre, String tipoPromo, double condicion)
			throws SQLException {

		if (tipoPromo.equals("ABSOLUTA")) {

			return new Absolutas(id, tipo, nombre, tipoPromo, condicion);
		}
		if (tipoPromo.equals("PORCENTUALES")) {

			return new Porcentuales(id, tipo, nombre, tipoPromo, condicion);
		} else {

			return new AXB(id, tipo, nombre, tipoPromo, condicion);
		}
	}

}