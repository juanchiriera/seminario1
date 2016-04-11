<%@page import="java.awt.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
				<h1 class="page-header" style="margin-top: 15px;">Modificacion
					de Empleado</h1>
			</div>
			<form method="post" action="ModificarEmpleadoServlet">
				<b>DNI: ${empleado.getDni()}</b> 
				<input type="text" id="dni" name="dni" style="visibility: hidden;" value="${empleado.getDni()}">
				<br><br>
				Nombre: 
				<input type="text" id="nombre" name="nombre" value="${empleado.getNombre()}" ></input>
				<br><br>
				Apellido: 
				<input type="text" id="apellido" name="apellido" value="${empleado.getApellido()}"></input>
				<br><br>
				Antiguedad: 
				<input type="text" id="antiguedad" name="antiguedad" value="${empleado.getAntiguedad()}"></input>
				<br><br>
				<button type="submit" class="btn btn-default">Confirmar</button>
			</form>


		</div>
	</div>

	</div>
</body>
	<%@ include file="/Bottom.html" %>
</html>
