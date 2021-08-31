package tierraMedia;

public abstract class Producto { // PREGUNTAR SI ES RECOMENDABLE QUE PRODUCTO SEA UNA SUBCLASE DE TURISMO.

	protected double costo;
	protected double tiempoDeDuracion;
	protected TIPO_DE_ATRACCION tipo;

	public Producto(double costo, double tiempo, TIPO_DE_ATRACCION tipo) {
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

}