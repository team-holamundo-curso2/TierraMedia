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

//FALTA ARMAR LAS EXCEPTION PARA ATAJAR LOS ERRORES ANTES DE LA CREACION DE CADA PROMOCION.
	
	/**
	* Lectura del archivo de promociones y creacion de la lista de promos.
	*/
	public List<Promociones> leerPromociones(List<Atracciones> atrList) {
		List<Promociones> promociones = new ArrayList<Promociones>();
		try {
			fr = new FileReader("archivos/promociones.txt");
			br = new BufferedReader(fr);
			String linea = br.readLine();
			while (linea != null) {
				promociones.addAll(crearPromocion(linea, atrList));
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
	
	/**
	* Creacion de las promociones de acuerdo a su tipo de promocion.
	*/
	private ArrayList<Promociones> crearPromocion(String linea, List<Atracciones> atracciones) {
		String[] lin = linea.split(",");
		List<Atracciones> atr = new ArrayList<Atracciones>();
		ArrayList<Promociones> nuevasPromociones = new ArrayList<Promociones>();
		if (TIPO_DE_PROMOCIONES.valueOf(lin[1].toUpperCase()).equals(TIPO_DE_PROMOCIONES.ABSOLUTAS)) {
			nuevasPromociones.add(promoAbsolutas(atracciones, lin, atr));
		} else if (TIPO_DE_PROMOCIONES.valueOf(lin[1].toUpperCase()).equals(TIPO_DE_PROMOCIONES.PORCENTUALES)) {
			nuevasPromociones.add(promoPorcentuales(atracciones, lin, atr));
		} else if (TIPO_DE_PROMOCIONES.valueOf(lin[1].toUpperCase()).equals(TIPO_DE_PROMOCIONES.AXB)) {
			nuevasPromociones.add(promoAXB(atracciones, lin, atr));
		}

		return nuevasPromociones;

	}
	
	/**
	* Creacion de las promociones de tipo Absolutas.
	*/
	private Absolutas promoAbsolutas(List<Atracciones> atracciones, String[] lin, List<Atracciones> atr) {
		for (Atracciones atraccion : atracciones) {
			for (int i = 5; i < 5 + Integer.parseInt(lin[4]); i++) {
				if (atraccion.obtenerNombre().equals(lin[i])) {
					atr.add(atraccion);
				}
			}
		}
		return new Absolutas(TIPO_DE_ATRACCION.valueOf(lin[0].toUpperCase()), lin[2],
				TIPO_DE_PROMOCIONES.valueOf(lin[1].toUpperCase()), Double.parseDouble(lin[3]), Integer.parseInt(lin[4]),
				atr);
	}
	
	/**
	* Creacion de las promociones de tipo Porcentuales. 
	*/
	private Porcentuales promoPorcentuales(List<Atracciones> atracciones, String[] lin, List<Atracciones> atr) {

		for (Atracciones atraccion : atracciones) {
			for (int i = 5; i < 5 + Integer.parseInt(lin[4]); i++) {
				if (atraccion.obtenerNombre().equals(lin[i])) {
					atr.add(atraccion);
				}
			}
		}

		return new Porcentuales(TIPO_DE_ATRACCION.valueOf(lin[0].toUpperCase()), lin[2],
				TIPO_DE_PROMOCIONES.valueOf(lin[1].toUpperCase()), Double.parseDouble(lin[3]), Integer.parseInt(lin[4]),
				atr);
	}

	/**
	* Creacion de las promociones de tipo AXB.
	*/
	private AXB promoAXB(List<Atracciones> atracciones, String[] lin, List<Atracciones> atr) {
		for (Atracciones atraccion : atracciones) {
			for (int i = 4; i < 4 + Integer.parseInt(lin[3]); i++) {
				if (atraccion.obtenerNombre().equals(lin[i])) {
					atr.add(atraccion);
				}
			}
		}

		return new AXB(TIPO_DE_ATRACCION.valueOf(lin[0].toUpperCase()), lin[2],
				TIPO_DE_PROMOCIONES.valueOf(lin[1].toUpperCase()), Integer.parseInt(lin[3]), atr);
	}

}