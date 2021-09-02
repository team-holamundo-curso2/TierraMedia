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

	public List<Usuario> leerUsuario() {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		try {
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
		return usuarios;
	}

	private Usuario crearUsuario(String linea) {
		String[] lin = linea.split(",");
		return new Usuario(lin[0], TIPO_DE_ATRACCION.valueOf(lin[1]), Integer.parseInt(lin[2]),
				Integer.parseInt(lin[3]));
	}
}
