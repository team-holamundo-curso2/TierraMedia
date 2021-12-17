<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal-body">
	<div class="mb-3">
		<label for="name" class="col-form-label">Nombre:</label> <input
			type="text" class="form-control" id="name" name="name"
			required value="${attraction.obtenerNombre()}">
	</div>
	<div class="mb-3">
		<label for="descripcion" class="col-form-label">Descripcion:</label> <input
			type="text" class="form-control" id="descripcion" name="descripcion"
			required value="${attraction.obtenerDescripcion()}">
	</div>
	<div class="mb-3">
		<label for="tipo" class="col-form-label">Tipo:</label> <input
			type="text" class="form-control" id="tipo" name="tipo"
			required value="${attraction.obtenerTipo()}">
	</div>
	<div class="mb-3">
		<label for="cost"
			class='col-form-label ${attraction.errores.get("costo") != null ? "is-invalid" : "" }'>Costo:</label>
		<input class="form-control" type="number" step="any" id="cost" name="cost"
			required value="${attraction.obtenerCosto()}"></input>
		<div class="invalid-feedback">
			<c:out value='${attraction.errores.get("costo")}'></c:out>
		</div>
	</div>
	<div class="mb-3">
		<label for="duration"
			class='col-form-label ${attraction.errores.get("tiempoDeDuracion") != null ? "is-invalid" : "" }'>Duration:</label>
		<input class="form-control" type="number" step="any" id="duration" name="duration"
			required value="${attraction.obtenerTiempo()}"></input>
		<div class="invalid-feedback">
			<c:out value='${attraction.errores.get("tiempoDeDuracion")}'></c:out>
		</div>
	</div>
	<div class="mb-3">
		<label for="capacity"
			class='col-form-label ${attraction.errores.get("cupoDePersonas") != null ? "is-invalid" : "" }'>Capacity:</label>
		<input class="form-control" type="number" id="capacity" name="capacity"
			required value="${attraction.obtenerCupo()}"></input>
		<div class="invalid-feedback">
			<c:out value='${attraction.errores.get("cupoDePersonas")}'></c:out>
		</div>
	</div>
</div>
<div>
	<button type="submit" class="btn btn-primary">Guardar</button>
	<a onclick="window.history.back();" class="btn btn-secondary"
		role="button">Cancelar</a>
</div>
