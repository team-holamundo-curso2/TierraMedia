package tierraMedia;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TurismoTierraMedia {

	private static List<Usuario> usuarios;
	private static List<Producto> productos = new ArrayList<Producto>();

	private LectorUsuario lector;

	public void usuariosRecibidos() {
		lector.leerUsuario(); // LEO ARCHIVO USUARIO.TXT Y CREA UNA LISTA DE USUARIOS.
	}

	public void sugerencias() {
		// ITERAR LISTAS DE Usuarios y Productos
		// Ordena las listas
		// Retorna los productos ordenados.

	}

	public void imprimirEnArchivoItinerario(List<Producto> itinerario, Usuario usuarioActual) throws IOException {
		PrintWriter salida = new PrintWriter(new FileWriter("Itinerario_De_" + usuarioActual.obtenerNombre() + ".out"));
		salida.println("Lista de Actividades");
		for (Producto actividad : itinerario) {
			salida.println(actividad.obtenerNombre());
		}
		salida.println(usuarioActual.resumenItinerario());
		salida.close();

	}

	public static void main(String[] args) {
		List<Atracciones> atraccionesN = null;
		List<Promociones> promos = null;

		LectorUsuario lec = new LectorUsuario();
		TurismoTierraMedia.usuarios = lec.leerUsuario();
		System.out.println(usuarios);

		System.out.println("___________________________");

		LectorAtracciones atr = new LectorAtracciones();
		atraccionesN = atr.leerAtracciones();
		System.out.println(atraccionesN);

		System.out.println("___________________________");

		LectorPromociones prom = new LectorPromociones();
		promos = prom.leerPromociones(atraccionesN);
		System.out.println(promos);

		System.out.println("___________________________");

		productos.addAll(atraccionesN);
		productos.addAll(promos);

		System.out.println(productos);

	}
}