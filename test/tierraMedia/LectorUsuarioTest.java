package tierraMedia;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class LectorUsuarioTest {
	String rutaU = "archivos/usuarioPrueba.txt";
	String rutaU2 = "archivos/usuario.txt";
	List<Usuario> usuarios;
	LectorUsuario lector = new LectorUsuario();

	@Test 
	public void pruebaDeCreacionDosUsuariosIncorrectos() {
		usuarios = lector.leerUsuario(rutaU);
		assertEquals(2, usuarios.size());
	}
	@Test 
	public void pruebaDeCreacionCuatroUsuariosCorrectos() {
		usuarios = lector.leerUsuario(rutaU2);
		assertEquals(4, usuarios.size());
	}

}