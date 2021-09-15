package tierraMedia;

import java.util.Objects;

public abstract class Producto { 
	protected double costo;
	protected double tiempoDeDuracion;
	protected TIPO_DE_ATRACCION tipo;
	protected String nombre;

	public Producto(TIPO_DE_ATRACCION tipo, String nombre) {
		this.tipo = tipo;
		this.nombre = nombre;
	}
	
	public Producto(TIPO_DE_ATRACCION tipo, String nombre,  double costo) throws UsuarioException{
		if (costo <= 0 ) {
			throw new UsuarioException("El producto ingresado tiene un valor incorrecto");
		}

		this.tipo = tipo;
		this.costo = costo;
		this.nombre = nombre;
	}

	
	public Producto(TIPO_DE_ATRACCION tipo, String nombre, double costo, double tiempo) throws UsuarioException{
		if (costo <= 0 || tiempo <= 0) {
			throw new UsuarioException("El producto ingresado tiene un valor incorrecto");
		}
		this.costo = costo;
		this.tiempoDeDuracion = tiempo;
		this.tipo = tipo;
		this.nombre = nombre;
	}

	public abstract boolean hayCupo();
	
	public abstract void restarCupo();
	
	protected double obtenerCosto() {
		return this.costo;

	}
	
	public abstract boolean contiene(Producto p);

	protected double obtenerTiempo() {
		return this.tiempoDeDuracion;
	}

	protected TIPO_DE_ATRACCION obtenerTipo() {
		return this.tipo;
	}
	
	protected String obtenerNombre() {
		return this.nombre;
	}
	
	@Override
	public String toString() { 
		return "Producto [costo=" + costo + ", tiempoDeDuracion=" + tiempoDeDuracion + ", tipo=" + tipo + "]";
	}

	public boolean esPromocion() {
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(costo, nombre, tiempoDeDuracion, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return Double.doubleToLongBits(costo) == Double.doubleToLongBits(other.costo)
				&& Objects.equals(nombre, other.nombre)
				&& Double.doubleToLongBits(tiempoDeDuracion) == Double.doubleToLongBits(other.tiempoDeDuracion)
				&& tipo == other.tipo;
	}

	}