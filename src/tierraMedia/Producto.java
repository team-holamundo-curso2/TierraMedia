package tierraMedia;

import java.util.Objects;

public abstract class Producto { // PREGUNTAR SI ES RECOMENDABLE QUE PRODUCTO SEA UNA SUBCLASE DE TURISMO.

	protected double costo;
	protected double tiempoDeDuracion;
	protected TIPO_DE_ATRACCION tipo;

	public Producto(TIPO_DE_ATRACCION tipo) {
		this.tipo = tipo;
	}

	public Producto(double costo, double tiempo, TIPO_DE_ATRACCION tipo) {
		this.costo = costo;
		this.tiempoDeDuracion = tiempo;
		this.tipo = tipo;
	}

	protected double obtenerCosto() {
		return this.costo;

	}

	protected double obtenerTiempo() {
		return this.tiempoDeDuracion;
	}

	protected TIPO_DE_ATRACCION obtenerTipo() {
		return this.tipo;
	}

	@Override
	public String toString() { // UNA VEZ CONSEGUIDO EL PODER GENERAR UNA LISTA DE PROMOCIONES, HAY QUE DEFINIR MEJOR ESTE TOSTRING
		return "Producto [costo=" + costo + ", tiempoDeDuracion=" + tiempoDeDuracion + ", tipo=" + tipo + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(costo, tiempoDeDuracion, tipo);
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
				&& Double.doubleToLongBits(tiempoDeDuracion) == Double.doubleToLongBits(other.tiempoDeDuracion)
				&& tipo == other.tipo;
	}
	
	
}