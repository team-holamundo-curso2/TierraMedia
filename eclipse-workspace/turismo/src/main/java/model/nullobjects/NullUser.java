package model.nullobjects;

import model.Usuario;
import persistence.commons.UsuarioException;

public class NullUser extends Usuario {

	public static Usuario build() throws UsuarioException {
		return new NullUser();
	}
	
	public NullUser() throws UsuarioException {
		super(0, "", 0, 0.0, 0.0, false, false);
	}
	@Override
	public boolean isNull() {
		return true;
	}
	
}
