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

	private Usuario crearUsuario(String linea) throws UsuarioException {
		String[] lin = linea.split(",");
		if(lin.length != 4) {
			throw new UsuarioException("La cantidad de argumento no son los correctos para un Usuario");
		}
		try{
			return new Usuario(lin[0], TIPO_DE_ATRACCION.valueOf(lin[1]), Double.parseDouble(lin[2]),
				Double.parseDouble(lin[2]));
		}catch(NumberFormatException UsuarioException) {
			throw new UsuarioException("La cantidad de argumento no son los correctos para un Usuario");
		}
	}
}