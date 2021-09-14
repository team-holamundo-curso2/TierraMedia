package tierraMedia;

import java.io.IOException;

public class App {

	public static void main(String[] args) throws IOException {
		String rutaA = "archivos/atracciones.txt";
		String rutaP = "archivos/promociones.txt";
		String rutaU = "archivos/usuario.txt";
		TurismoTierraMedia appTurismo = new TurismoTierraMedia(rutaU, rutaA, rutaP);
		
		appTurismo.crearListas();
		appTurismo.sugerencias();
		

	}

}