package tierraMedia;
/***
 *Hay un error en la lectura o creacion de Usuario
 */
@SuppressWarnings("serial")
public class UsuarioException extends RuntimeException {

	public UsuarioException(String string) {
		super(string);
	}

}