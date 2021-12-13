package persistence.commons;

/***
 * Hay un error en la creacion o lectura de atracciones
 *
 */
@SuppressWarnings("serial")
public class AtraccionException extends RuntimeException {

	public AtraccionException(String string) {
		super(string);
	}

}