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

	
	// EN ESTE METODO SE CREA EL LECTOR PARA QUE PUEDA GENERAR LA LISTA DE USUARIOS LEVANTANDO LOS DATOS DEL ARCHIVO. 
	public void usuariosRecibidos() {
		this.lector = new LectorUsuario();
		this.usuarios = this.lector.leerUsuario(); // LEO ARCHIVO USUARIO.TXT Y CREA UNA LISTA DE USUARIOS.
	}
	
	//METODO PARA ORDENAR LAS LISTAS, Y RETORNAR UNA LISTA ORDENADA PARA OFRECERLE A CADA USUARIO SEGUN PREFERENCIA
	//NO SE PUDO TERMINAR PORQUE NO ME ESTARIA ANDANDO EL COMPARADOR DENTRO DE OFERTABLES... ME TIRA ERROR DE COMPATIBILIDAD
	public List<Producto> ordenarProductos (Usuario user, List<Producto> productosAOrdenar) {
		this.productos = productosAOrdenar;
		
		
	this.productos.sort(new OfertablesPorPreferencia("preferencia"));
		
		
		
		
		return this.productos;
		
	}
//METO DONDE SE RECORRE LA LISTA DE USUARIOS Y A C/U SE LE OFRECE UNA LISTA DE PRODUCTOS ORDENADA. 
	public void sugerencias(List<Usuario> usuarios, List<Producto> productosAOrdenar) {
	
			for (Usuario user : usuarios) {
			this.productos.addAll(ordenarProductos(user, productosAOrdenar));
		
			user.ofertasAceptadas(productos);
		}
	}

//METODO QUE GENERA EL ARCHIVO DE SALIDA. 
	public void imprimirEnArchivoItinerario(Usuario usuarioActual) throws IOException {
		PrintWriter salida = new PrintWriter(new FileWriter("Itinerario_De_" + usuarioActual.obtenerNombre() + ".out"));
		salida.println("Lista de Actividades");
		for (Producto actividad : usuarioActual.itinerario) {
			salida.println(actividad.obtenerNombre());
		}
		salida.println(usuarioActual.resumenItinerario());
		salida.close();

	}

	public static void main(String[] args) throws IOException {
		List<Usuario> usuarios = null;
		List<Atracciones> atraccionesN = null;
		List<Promociones> promos = null;
		List<Producto> productos = new ArrayList<Producto>();

		LectorUsuario lec = new LectorUsuario();
		usuarios = lec.leerUsuario();
		System.out.println(usuarios);

		System.out.println("____________1_______________");

		LectorAtracciones atr = new LectorAtracciones();
		atraccionesN = atr.leerAtracciones();
		System.out.println(atraccionesN);

		System.out.println("____________2_______________");

		LectorPromociones prom = new LectorPromociones();
		promos = prom.leerPromociones(atraccionesN);
		System.out.println(promos);

		System.out.println("____________3______________");

		productos.addAll(atraccionesN);
		productos.addAll(promos);
		System.out.println(productos);
		
		// productos.sort(new OfertablesPorPreferencia("TIPO_DE_ATRACCION"));   ===> ESTE AUN NO ESTA LISTO EL COMPARE
		
		
		
		System.out.println("____________4_______________");
/*
		for (Usuario user : usuarios) {
			user.ofertasAceptadas(productos);
			System.out.println(user.resumenItinerario());
		}
		
		*/
		System.out.println("___________________________");
		
		System.out.println("___________________________");
		
		Usuario Pepita = new Usuario("Pepita", TIPO_DE_ATRACCION.AVENTURA, 50, 20);
		Pepita.itinerario = productos;
		System.out.println(Pepita.obtenerItinerario());
		System.out.println(Pepita.resumenItinerario());
		
		System.out.println("____________5_______________");
		
		TurismoTierraMedia turismoApp = new TurismoTierraMedia();
		turismoApp.usuariosRecibidos();
		System.out.println(turismoApp.usuarios);
		turismoApp.imprimirEnArchivoItinerario(Pepita);
		
		
	}
}