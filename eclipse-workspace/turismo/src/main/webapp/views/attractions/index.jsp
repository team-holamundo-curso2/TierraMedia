<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/partials/head.jsp"></jsp:include>
</head>
<body>

	<jsp:include page="/partials/nav.jsp"></jsp:include>

	<main class="container">

		<c:if test="${flash != null}">
			<div class="alert alert-danger">
				<p>
					<c:out value="${flash}" />
					<c:if test="${errors != null}">
						<ul>
						<c:set var="entry"></c:set>
							<c:forEach items="${errors}" var="entry">
								<li><c:out value="${entry.getValue()}"></c:out></li>
							</c:forEach>
						</ul>
					</c:if>
				</p>
			</div>
		</c:if>

		<div class="bg-light p-4 mb-3 rounded">
			<h1>Estas son las atracciones de la Tierra Media</h1>
		</div>

		<c:if test="${user.esAdmin()}">
			<div class="mb-3">
				<a href="/turismo/attractions/create.do" class="btn btn-primary"
					role="button"> <i class="bi bi-plus-lg"></i> Nueva Atracción
				</a>
			</div>
		</c:if>
		<table class="table table-stripped table-hover">
			<thead>
				<tr>
					<th>Atracci&oacute;n</th>
					<th>Costo</th>
					<th>Duraci&oacute;n</th>
					<th>Cupo</th>
					<th>Acciones</th>
				</tr>
			</thead>
			<tbody>
			
				<c:forEach items="${atraccion}" var="atraccion">
					<tr>
						<td><strong><c:out value="${atraccion.obtenerNombre()}"></c:out></strong>
							 <p><c:out value="${atraccion.obtenerDescripcion()}"></c:out></p> </td>
						<td><c:out value="${atraccion.obtenerCosto()}"></c:out></td>
						<td><c:out value="${atraccion.obtenerTiempo()}"></c:out></td>
						<td><c:out value="${atraccion.obtenerCupo()}"></c:out></td>

						<td><c:if test="${user.esAdmin()}">
								<a href="/turismo/attractions/edit.do?id=${atraccion.obtenerId()}"
									class="btn btn-light rounded-0" role="button"><i
									class="bi bi-pencil-fill"></i></a>
								<a href="/turismo/attractions/delete.do?id=${atraccion.obtenerId()}"
									class="btn btn-danger rounded" role="button"><i
									class="bi bi-x-circle-fill"></i></a>
							</c:if> <c:choose>

								<c:when
									test="${user.puedePagarlo(atraccion) && user.tieneTiempo(atraccion) && atraccion.hayCupo() && !atraccion.estaBorrado()}">
									<a href="/turismo/attractions/buy.do?id=${atraccion.obtenerId()}"
										class="btn btn-success rounded" role="button">Comprar</a>
								</c:when>
								<c:otherwise>
									<a href="#" class="btn btn-secondary rounded disabled"
										role="button">No se puede comprar</a>
								</c:otherwise>
							</c:choose></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</main>

</body>
</html>