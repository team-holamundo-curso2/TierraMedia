package tierraMedia;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ofertable {

	//Metodo que reciba archivo de promos y atracciones y cree la lista de productos
	
	//Metodo que reciba usuario e inicie el proceso de oferta
	
	
	
	// METODO QUE ESTABLECE LAS CONDICIONES PARA USAR "ACEPTAR" Y SETEA EL
		// ITINERARIO.
		public void ofertasAceptadas(List<Producto> productosAOfrecer) throws AtraccionException {
			List<Producto> prod = new ArrayList<Producto>();

			for (Producto ofrecer : productosAOfrecer)  {
				if (this.monedas > ofrecer.costo && this.tiempoDisponible > ofrecer.tiempoDeDuracion) {

					if (ofertarProducto(ofrecer)) {
						aceptar(ofrecer);
						prod.add(ofrecer);
					}
				}
			}

			this.itinerario = prod;
		}

		// METODO BOOLEANO PARA IMPRIMIR POR PANTALLA LAS OFERTAS Y SI EL USUARIO QUIERE
		// O NO ACEPTARLAS.
		public boolean ofertarProducto(Producto productoAofrecer) throws AtraccionException {
			String eleccion = "";
			Scanner respuesta = new Scanner(System.in);

			System.out.println("¿Acepta agregar a su itinerario " + productoAofrecer.nombre + "?");
			System.out.println("Si desea aceptar la oferta responda Si, en caso contrario escriba No");
			
				eleccion = respuesta.next().toUpperCase();
				System.out.println();
				while (!eleccion.equals("SI") && !eleccion.equals("NO")) {
					System.out.println("Ingrese Si o No");
					eleccion = respuesta.next().toUpperCase();
				}
				
				return eleccion.equals("SI");
			} 
		

		
		// METODO PARA OBTENER EL RESUMEN DEL ITINERARIO (NOMBRE DE LOS PRODUCTOS +
		// GASTOS Y TIEMPOS TOTALES)
		public String resumenItinerario() {
			double costoTotal = 0;
			double tiempoTotal = 0;
			for (Producto suma : this.itinerario) {
				costoTotal += suma.obtenerCosto();
				tiempoTotal += suma.obtenerTiempo();
			}
			return  "Costo Total =" + costoTotal + ", Tiempo Total ="
					+ tiempoTotal;

		}
		
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

}
