package tierraMedia;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LectorPromociones {
	private FileReader fr = null;
	private BufferedReader br = null;

	public List<Promociones> leerPromociones() {
		List<Promociones> promociones = new ArrayList<Promociones>();
		try {
			fr = new FileReader("archivos/promociones.txt");
			br = new BufferedReader(fr);
			String linea = br.readLine();
			while (linea != null) {
				Promociones nuevaPromocion = crearPromocion(linea);
				promociones.add(nuevaPromocion);
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
		return promociones;
	}

//If (tipo de promocion?) y invoque a los constructores que corresponden.
//da error porque promociones quedo como abstracta
	private Promociones crearPromocion(String linea) {
		String[] lin = linea.split(",");
		return new Promociones(TIPO_DE_ATRACCION.valueOf(lin[0]), TIPO_DE_PROMOCIONES.valueOf(lin[1]), // HAY QUE
																										// DEFINIR MEJOR
																										// EL
																										// CONSTRUCTOR
																										// DE
																										// PROMOCIONES.
				Integer.parseInt(lin[2]), null); // SEGUN EL EJEMPLO QUE EL PROFE DIO EN CLASE, QUE ES DISTINTO A COMO
		// LO TENEMOS NOSOTROS EN NUESTRO TXT. QUEDO ESO PARA REVISAR.
	}
}
