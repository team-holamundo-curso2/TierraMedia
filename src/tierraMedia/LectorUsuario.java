package tierraMedia;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LectorUsuario {
	private FileReader fr = null;
	private BufferedReader br = null;

	/**
	 * Lee el archivo de usuarios y crea la lista de usuarios.
	 */
	public List<Usuario> leerUsuario(String rutaUsuarios) {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		try {
			fr = new FileReader(rutaUsuarios);
			br = new BufferedReader(fr);
			String linea;
			while ((linea = br.readLine()) != null) {
				try {
					Usuario nuevoUsuario = crearUsuario(linea);
					usuarios.add(nuevoUsuario);
				} catch (UsuarioException rne) {
					System.err.println(rne.getMessage());
				}catch (NumberFormatException error) {
					System.err.println("El formato es incorrecto");
				}

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
		return usuarios;
	}

	/**
	 * Crea cada usuario con los datos de archivo.
	 */
	private Usuario crearUsuario(String linea) throws UsuarioException {
		String[] lin = linea.split(",");

		if (lin.length != 4) {
			throw new UsuarioException("La cantidad de argumentos no son los correctos para un Usuario");
		}
		
		return new Usuario(lin[0], TIPO_DE_ATRACCION.valueOf(lin[1]), Double.parseDouble(lin[2]),
				Double.parseDouble(lin[3]));
	}

}
