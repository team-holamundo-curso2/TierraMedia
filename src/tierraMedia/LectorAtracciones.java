package tierraMedia;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LectorAtracciones {
	private FileReader fr = null;
	private BufferedReader br = null;

	/**
	 * Lectura del archivo de atracciones y creacion de la lista de atracciones.
	 */
	public List<Atracciones> leerAtracciones(String rutaAtracciones) {
		List<Atracciones> atracciones = new ArrayList<Atracciones>();
		try {
			fr = new FileReader(rutaAtracciones);
			br = new BufferedReader(fr);
			String linea;
			while ((linea = br.readLine()) != null) {
				try {
					Atracciones nuevaAtraccion = crearAtraccion(linea);
					atracciones.add(nuevaAtraccion);
				} catch (ProductoException mti) {
					System.err.println(mti.getMessage());
				} catch (NumberFormatException error) {
					System.err.println("El formato es incorrecto");
				} catch (AtraccionException aerror) {
					System.err.println(aerror.getMessage());
				}
			}
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fr != null) {
					fr.close();
				}
			}

			catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return atracciones;
	}

	/**
	 * Creacion de cada atraccion a partir de los datos leidos por el archivo.
	 */
	private Atracciones crearAtraccion(String linea) throws AtraccionException {
		String[] lin = linea.split(",");
		if (lin.length != 5) {
			throw new AtraccionException("La cantidad de argumentos no son los correctos para esta atraccion");
		}

		return new Atracciones(TIPO_DE_ATRACCION.valueOf(lin[4]), lin[0], Double.parseDouble(lin[1]),
				Double.parseDouble(lin[2]), Integer.parseInt(lin[3]));
	}

}
