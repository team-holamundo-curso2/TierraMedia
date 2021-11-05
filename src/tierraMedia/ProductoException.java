package tierraMedia;

/***
 * Hay un error en la creacion de un producto
 *
 */
@SuppressWarnings("serial")
public class ProductoException extends RuntimeException {

	public ProductoException(String string) {
		super(string);
	}

}
