package tierraMedia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TurismoTierraMedia {

	List<Usuario> usuarios;
	List<Atracciones> atrLista;
	List<Producto> productosDesordenados;
	private Ofertable ofertador;
	private String rutaAtracciones;
	private String rutaPromociones;
	private String rutaUsuarios;

	public TurismoTierraMedia(String rutaU, String rutaA, String rutaP) {
		this.rutaAtracciones = rutaA;
		this.rutaPromociones = rutaP;
		this.rutaUsuarios = rutaU;
	}

	public void crearListas() {
		this.crearListaDeUsuarios();
		this.crearListasDeProductos();
	}

	public void crearListaDeUsuarios() {
		LectorUsuario lector = new LectorUsuario();
		this.usuarios = lector.leerUsuario(this.rutaUsuarios);
	}

	public List<Producto> crearListasDeProductos() {
		
		List<Producto> productos = new ArrayList<Producto>();

		LectorAtracciones atr = new LectorAtracciones();
		this.atrLista = atr.leerAtracciones(this.rutaAtracciones);
		LectorPromociones prom = new LectorPromociones();
		productos.addAll(prom.leerPromociones(atrLista, this.rutaPromociones));
		productos.addAll(atrLista);

		return this.productosDesordenados = productos;
	}

	
	public List<Producto> ordenarProductos(Usuario user) {
		
		List<Producto> productosOrdenados = new ArrayList<Producto>();

		this.productosDesordenados.sort(new OfertablesPorPreferencia(user.obtenerPreferencia()));
		productosOrdenados.addAll(productosDesordenados);

		return productosOrdenados;
	}

	public void sugerencias() throws IOException {

		for (Usuario user : this.usuarios) {

			this.ofertador = new Ofertable(user, this.ordenarProductos(user));
			System.out.println("Bienvenido" + user.obtenerNombre());
			this.ofertador.ofertarProducto();
			System.out.println(
					"Muchas Gracias" + " " + user.obtenerNombre() + " " + "por usar nuestros servicios y productos");
			this.ofertador.imprimirEnArchivoItinerario();
		}

	}

}