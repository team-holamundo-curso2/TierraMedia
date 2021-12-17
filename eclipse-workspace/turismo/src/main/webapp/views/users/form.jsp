<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="mb-3">
	<label for="name" class="col-form-label">Nombre:</label> <input
		type="text" class="form-control" id="name" name="nombre"
		required value="${tmp_user.obtenerNombre()}">
</div>
<div class="mb-3">
	<label for="coins"
		class='col-form-label ${tmp_user.errors.get("monedas") != null ? "is-invalid" : "" }'>Monedas:</label>
	<input class="form-control" type="number" step="any" id="coins" name="monedas"
		required value="${tmp_user.obtenerMonedas()}"></input>
	<div class="invalid-feedback">
		<c:out value='${tmp_user.errors.get("monedas")}'></c:out>
	</div>
</div>

<div class="mb-3">
	<label for="time"
		class='col-form-label ${tmp_user.errors.get("tiempo") != null ? "is-invalid" : "" }'>Tiempo:</label>
	<input class="form-control" type="number" step="any" id="time" name="tiempo"
		required value="${tmp_user.obtenerTiempo()}"></input>
	<div class="invalid-feedback">
		<c:out value='${tmp_user.errors.get("tiempo")}'></c:out>
	</div>
	<div class="mb-3">
		<label for="preferencia" class="col-form-label">Preferencia:</label> <input
			type="number" class="form-control" id="preferencia" name="preferencia"
			required value="${tmp_user.obtenerPreferencia()}">
	</div>
</div>


<div>
	<button type="submit" class="btn btn-primary">Guardar</button>
	<a onclick="window.history.back();" class="btn btn-secondary"
		role="button">Cancelar</a>
</div>
