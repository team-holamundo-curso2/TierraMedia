package tierraMedia;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TurismoTierraMedia {

	private List<Usuario> usuarios;


	private List<Atracciones> atrLista;
	private List<Producto> productosDesordenados;



	private Ofertable ofertador;


	public TurismoTierraMedia() {
		}

	// Crea todas las listas necesarias.
	public void crearListas() throws SQLException {
		this.crearListasDeProductos();
		this.crearListaDeUsuarios();
		}

	public void crearListaDeUsuarios() throws SQLException {
		UsuarioDAO userD = new UsuarioDAO();
		this.usuarios = userD.crearListaDeUsuarios();
	}

	public List<Producto> crearListasDeProductos() throws SQLException {
		List<Producto> productos = new ArrayList<Producto>();

		AtraccionesDAO atrD = new AtraccionesDAO();
		this.atrLista = atrD.crearListaDeAtracciones();
		PromocionesDAO promD = new PromocionesDAO();
		productos.addAll(promD.crearListaDePromociones(this.atrLista));
		productos.addAll(atrLista);

		return this.productosDesordenados = productos;
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public List<Producto> getProductosDesordenados() {
		return productosDesordenados;
	}

	// Metodo para ordenar los productos segun preferencia del usuario.
	public List<Producto> ordenarProductos(Usuario user) {

		List<Producto> productosOrdenados = new ArrayList<Producto>();

		this.productosDesordenados.sort(new OfertablesPorPreferencia(user.obtenerPreferencia()));
		productosOrdenados.addAll(productosDesordenados);

		return productosOrdenados;
	}

	// Metodo que realiza las sugerencias a los usuarios.
	public void sugerencias() throws IOException, AtraccionException, SQLException {

		for (Usuario user : this.usuarios) {
			user.consultarItinerario(productosDesordenados, user);
			this.ofertador = new Ofertable(user, this.ordenarProductos(user));
			System.out.println("Bienvenido" + " " + user.obtenerNombre());
			this.ofertador.ofertarProducto();
			System.out.println(
					"Muchas Gracias" + " " + user.obtenerNombre() + " " + "por usar nuestros servicios y productos");
			System.out.println("T  U  R  I  S  M  O  -  T  I  E  R  R  A  -  M  E  D  I  A");
			this.ofertador.armarItinerario(user);
		}

	}

	public void setProductosDesordenados(List<Producto> productosDesordenados) {
		this.productosDesordenados = productosDesordenados;
	}

}