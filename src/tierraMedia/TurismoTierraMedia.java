package tierraMedia;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class TurismoTierraMedia {

	private List<Usuario> usuarios;
	private List<Producto> productos;
	private LectorUsuario lector;

	// EN ESTE METODO SE CREA EL LECTOR PARA QUE PUEDA GENERAR LA LISTA DE USUARIOS
	// LEVANTANDO LOS DATOS DEL ARCHIVO.
	public void usuariosRecibidos() {
		this.lector = new LectorUsuario();
		this.usuarios = this.lector.leerUsuario(); // LEO ARCHIVO USUARIO.TXT Y CREA UNA LISTA DE USUARIOS.
	}
	
	//Aca quedaria un metodo que para cada usuario le genere el itinerario, pasando la lista de usuarios 
	// y los nombre de los archivos de promos y atracciones
	
	

	/**
	 * Para mudar?
	 */
	// METODO PARA ORDENAR LAS LISTAS, Y RETORNAR UNA LISTA ORDENADA PARA OFRECERLE
	// A CADA USUARIO SEGUN PREFERENCIA
	public List<Producto> ordenarProductos(Usuario user, List<Producto> productosAOrdenar) {

		productosAOrdenar.sort(new OfertablesPorPreferencia(user.preferencia));
		this.productos = productosAOrdenar;

		return this.productos;

	}

	// METO DONDE SE RECORRE LA LISTA DE USUARIOS Y A C/U SE LE OFRECE UNA LISTA DE
	// PRODUCTOS ORDENADA.
	public void sugerencias(List<Producto> productosAOrdenar) throws IOException {

		for (Usuario user : this.usuarios) {
			System.out.println("Bienvenido" + " " + user.obtenerNombre());
			ordenarProductos(user, productosAOrdenar);
			user.ofertasAceptadas(this.productos);
			System.out.println(
					"Muchas Gracias" + " " + user.obtenerNombre() + " " + "por usar nuestros servicios y productos");
			this.imprimirEnArchivoItinerario(user);
		}
	}

	// METODO QUE GENERA EL ARCHIVO DE SALIDA.
	public void imprimirEnArchivoItinerario(Usuario usuarioActual) throws IOException {
		PrintWriter salida = new PrintWriter(new FileWriter("Itinerario_De_" + usuarioActual.obtenerNombre() + ".out"));
		salida.println("Lista de Actividades");
		for (Producto actividad : usuarioActual.itinerario) {
			salida.println(actividad.obtenerNombre());
		}
		salida.println(usuarioActual.resumenItinerario());
		salida.close();

	}
	
	/**
	 * Fin para mudar
	 */

	public static void main(String[] args) throws IOException {
		TurismoTierraMedia app = new TurismoTierraMedia();
		List<Atracciones> atracciones = new ArrayList<Atracciones>();
		List<Promociones> promos = new ArrayList<Promociones>();
		List<Producto> productosDesordenados = new ArrayList<Producto>();

		LectorAtracciones atr = new LectorAtracciones();
		atracciones = atr.leerAtracciones();
		System.out.println(atracciones);

		LectorPromociones prom = new LectorPromociones();
		promos = prom.leerPromociones(atracciones);
		System.out.println(promos);

		productosDesordenados.addAll(atracciones);
		System.out.println(productosDesordenados);
		productosDesordenados.addAll(promos);
		System.out.println(productosDesordenados);

		app.usuariosRecibidos();
		System.out.println(app.usuarios);

		System.out.println("__________________________");

		for (Usuario user : app.usuarios) {
			app.ordenarProductos(user, productosDesordenados);
			System.out.println(app.productos);
		}

		System.out.println("__________________________");
		app.sugerencias(productosDesordenados);
	}
}