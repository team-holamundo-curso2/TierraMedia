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
			Promociones promo = crearPromociones(resultados);
			//Calculo el costo de cada promocion aca? (porque lo tiene que hacer por cada una tambien)
			//No estoy segura 
			//aplicar a cada promocion el asignarCosto o el aplicarPromociones por cada una.
			
			if (mapPromo.containsKey(promo)) {
				List<Atracciones> atr = mapPromo.get(promo);
				atr.addAll(consultarAtracciones(promo, atraccionesD));
				mapPromo.put(promo, atr);
			} else {
				List<Atracciones> atr = new ArrayList<Atracciones>();
				atr.addAll(consultarAtracciones(promo, atraccionesD));
				mapPromo.put(promo, atr);
			}
		}
		for (Map.Entry<Promociones, List<Atracciones>> entry : mapPromo.entrySet()) {
			entry.getKey().asignarListaAtracciones(entry.getValue());
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
		statement.setInt(1, promo.obtenerIdPromocion());
		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {
			for (Atracciones atraccion : atraccionesD) {
				if (resultado.getInt(1) == atraccion.obtenerIdAtraccion()) {
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

	public int queTipo() throws SQLException {
		String sql = "SELECT PROMOCION.TIPO_PROMO_ID FROM PROMOCION";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet resultados = statement.executeQuery();

		resultados.next();
		int tipo = resultados.getInt(1);
		return tipo;
	}

	private Promociones crearPromociones(ResultSet resultados) throws SQLException {
		PromocionesDAO promo = new PromocionesDAO();
		if (promo.queTipo() == 1) {
			return new Absolutas(resultados.getInt(1), resultados.getString(4), resultados.getString(2), resultados.getString(3),
					resultados.getDouble(5));
		}
		if (promo.queTipo() == 3) {
			return new Porcentuales(resultados.getInt(1), resultados.getString(4), resultados.getString(2), resultados.getString(3),
					resultados.getDouble(5));
		} else {
			return new AXB(resultados.getInt(1), resultados.getString(4), resultados.getString(2), resultados.getString(3), resultados.getDouble(5));
		}
	}

	public static void main(String[] args) throws SQLException {
		List<Promociones> promociones = new ArrayList<Promociones>();
		AtraccionesDAO atrDAO = new AtraccionesDAO();
		System.out.println(atrDAO.crearListaDeAtracciones());
		PromocionesDAO promoDAO = new PromocionesDAO();
		System.out.println(promoDAO.crearListaDePromociones(atrDAO.crearListaDeAtracciones()));
		System.out.println(promociones.addAll(promoDAO.crearListaDePromociones(atrDAO.crearListaDeAtracciones())));
		System.out.println(promociones.get(0).atracciones.get(0).obtenerIdAtraccion());
		System.out.println(promociones.get(0).atracciones.get(0).obtenerNombre());
		promociones.get(0).aplicarPromocion();
		System.out.println(promociones.get(0).obtenerCosto());
		promociones.get(1).aplicarPromocion();
		System.out.println(promociones.get(1).obtenerCosto());
		System.out.println(promociones.get(1).obtenerAtracciones());
		
		System.out.println(promociones.get(2).obtenerCosto());
		
		promociones.get(3).aplicarPromocion();
		System.out.println(promociones.get(3).obtenerCosto());
		System.out.println(promociones.get(3).obtenerAtracciones());
		System.out.println(promociones.get(3).atracciones.get(0).obtenerCosto());
		System.out.println(promociones.get(3).atracciones.get(1).obtenerCosto());
		System.out.println(promociones.get(3).atracciones.get(2).obtenerCosto());
		
		

		

	}
}