package tierraMedia;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Ofertable {
	private Usuario user;
	private List<Producto> productos;

	public Ofertable(Usuario user, List<Producto> productos) {
		this.user = user;
		this.productos = productos;
	}

	public boolean contieneEnItinerario(Producto producto) {
		boolean contiene = false;
		Iterator<Producto> iterador = this.user.obtenerItinerario().iterator();
		while (!contiene && iterador.hasNext()) {

			contiene = iterador.next().contiene(producto);
		}
		return contiene;
	}

	// Metodo que muestra por consola la oferta al usuario, para que acepte o no.
	public boolean mostrarProductoAlUsuario(Producto productoAofrecer, Usuario user) throws AtraccionException {
		String eleccion = "";
		Scanner respuesta = new Scanner(System.in);

		System.out.println("¿Acepta agregar a su itinerario " + productoAofrecer.obtenerNombre() + "?");
		System.out.println("Si desea aceptar la oferta responda Si, en caso contrario escriba No");

		eleccion = respuesta.next().toUpperCase();
		System.out.println();
		while (!eleccion.equals("SI") && !eleccion.equals("NO")) {
			System.out.println("Ingrese Si o No");
			eleccion = respuesta.next().toUpperCase();
		}

		return eleccion.equals("SI");
	}

	// Metodo del proceso de oferta
	public void ofertarProducto() throws AtraccionException, SQLException {
		List<Producto> productosAceptados = new ArrayList<Producto>();

		for (Producto ofrecer : this.productos) {
			if (this.user.obtenerMonedas() >= ofrecer.obtenerCosto()
					&& this.user.obtenerTiempoDisponible() >= ofrecer.obtenerTiempo() && ofrecer.hayCupo()
					&& !contieneEnItinerario(ofrecer)) {
				if (mostrarProductoAlUsuario(ofrecer, this.user)) {
					this.user.aceptar(ofrecer);
					user.crearItinerario(ofrecer);
					ofrecer.restarCupo();
					productosAceptados.add(ofrecer);
				}
			}
		}
            UsuarioDAO userD = new UsuarioDAO();
            userD.actualizarItinerario(this.user);
	}

	// Metodo que genera el archivo de salida.
	public void imprimirEnArchivoItinerario() throws IOException {
		PrintWriter salida = new PrintWriter(new FileWriter("Itinerario_De_" + user.obtenerNombre() + ".out"));
		salida.println("Lista de Actividades");
		for (Producto actividad : user.obtenerItinerario()) {
			salida.println(actividad.obtenerNombre());
		}
		salida.println(user.resumenItinerario());
		salida.close();

	}

}