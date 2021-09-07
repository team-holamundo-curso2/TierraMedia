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

	public List<Atracciones> leerAtracciones()  {
		List<Atracciones> atracciones = new ArrayList<Atracciones>();
		try {
			fr = new FileReader("archivos/atracciones.txt");
			br = new BufferedReader(fr);
			String linea = br.readLine();
			while (linea != null) {

				Atracciones nuevaAtraccion = crearAtraccion(linea);
				atracciones.add(nuevaAtraccion);
				linea = br.readLine();

			}
		} catch (FileNotFoundException e) {
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

	private Atracciones crearAtraccion(String linea) throws AtraccionException {
		String[] lin = linea.split(",");
		if (lin.length != 5) {
			throw new AtraccionException("La cantidad de argumentos no son los correctos para un Usuario");
		}
		try {
			return new Atracciones(TIPO_DE_ATRACCION.valueOf(lin[4]), lin[0], Double.parseDouble(lin[1]),
					Double.parseDouble(lin[2]), Integer.parseInt(lin[3]));

		} catch (NumberFormatException AtraccionException) {
			throw new AtraccionException("Parametro con numero invalido");
		}
	}

}
