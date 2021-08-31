package tierraMedia;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Lector {

	private FileReader fr = null;
	private BufferedReader br = null;

	public void leerUsuario() {
		try {
			List<Usuario> usuarios = new ArrayList<Usuario>();
			fr = new FileReader("archivos/usuario.txt");
			br = new BufferedReader(fr);
			String linea = br.readLine();
			while (linea != null) {
				Usuario nuevoUsuario = crearUsuario(linea);
				usuarios.add(nuevoUsuario);
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
	}

	private Usuario crearUsuario(String linea) {
		String[] lin = linea.split(",");
		return new Usuario(lin[0], TIPO_DE_ATRACCION.valueOf(lin[1]), Integer.parseInt(lin[2]),
				Integer.parseInt(lin[3]));
	}

	public void leerAtracciones() {
		try {
			List<Atracciones> atracciones = new ArrayList<Atracciones>();
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
	}

	private Atracciones crearAtraccion(String linea) {
		String[] lin = linea.split(",");
		return new Atracciones(lin[0], Integer.parseInt(lin[1]), Integer.parseInt(lin[2]), Integer.parseInt(lin[3]),
				TIPO_DE_ATRACCION.valueOf(lin[4]));
	}
}
