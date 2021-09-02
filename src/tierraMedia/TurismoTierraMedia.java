package tierraMedia;

import java.util.ArrayList;
import java.util.List;

public class TurismoTierraMedia {

	private static List<Usuario> usuarios;
	private static List<Producto> productos = new ArrayList<Producto>();
	private static List<Atracciones> atracciones;

	private LectorUsuario lector;

	public void usuariosRecibidos() {
		lector.leerUsuario(); // LEO ARCHIVO USUARIO.TXT Y CREA UNA LISTA DE USUARIOS.
	}

	public void sugerencias() {
		// ITERAR LISTAS DE Usuarios y Productos
		// Ordena las listas
		// Retorna los productos ordenados.

	}

	public static void main(String[] args) {

		LectorUsuario lec = new LectorUsuario();
		TurismoTierraMedia.usuarios = lec.leerUsuario();
		System.out.println(usuarios);

		System.out.println("___________________________");

		LectorAtracciones atr = new LectorAtracciones();
		TurismoTierraMedia.atracciones = atr.leerAtracciones();
		System.out.println(atracciones);

		System.out.println("___________________________");

		for (Atracciones atraccion : atracciones) {
			productos.add(atraccion);
		}
		System.out.println(productos);

	}
}