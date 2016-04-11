<%@page import="java.awt.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %>
<% 
 if ((session.getAttribute("login") == null) || (session.getAttribute("login") == "")) {
  response.sendRedirect("./index.jsp");
 }

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<%@ include file="/head.html"%>
</head>

<body>

	<%@ include file="/nav.html"%>

	<div class="container-fluid">
		<div class="row">

			<div class="2 main">
				<div></div>
				<h1 class="page-header" style="margin-top: 15px;">
					Docentes
				</h1>
			</div>

			<div class="col-xs-12">
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>Apellido</th>
								<th>Nombre</th>
								<th>DNI</th>
								<th>CUIL</th>
								<th>Fecha Nacimiento</th>
								<th>Antiguedad</th>
								<th>Materia</th>
								<th>Curso</th>
								<th>Division</th>
								<th>Estado</th>
							</tr>
						</thead>


						<c:forEach var="docente" items="${docentes}">
							<tr>
								<td>${docente.getApellido()}</td>
								<td>${docente.getNombre()}</td>
								<td>${docente.getDni()}</td>
								<td>${docente.getCuil()}</td>
								<td>${docente.getFechaDeNacimiento()}</td>
								<td>${docente.getAntiguedad()}</td>
								<td>${docente.getMateria()}</td>
								<td>${docente.getCurso()}</td>
								<td>${docente.getDivision()}</td>
								<td>${docente.getEstado()}</td>
							</tr>
						</c:forEach>

					</table>
				</div>
			</div>
		</div>
	</div>
</body>
	<%@ include file="/Bottom.html" %>
</html>

