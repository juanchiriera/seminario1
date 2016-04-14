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
				<h1 class="page-header" style="
					margin-top: 15px;
					margin-left: 18px;">
					Modificación de Empleado
				</h1>
				
				<!-- Formularios-->
				
				<div class="col-xs-12">
					<form method="post" action="ModificarEmpleadoServlet" style="
						background-color: #d9edf7;
					    border-radius: 5px;
					    display: block;
					    padding: 30px;
					    padding-left: 45px;
					    width: 50%;
					    padding-right: 45px;
						padding-top: 30;">
					    <div class="izquierda">	
						<b>DNI: ${empleado.getDni()}</b> 
						<input type="text" id="dni" name="dni" style="visibility: hidden;" value="${empleado.getDni()}">
						<br><br>
						<label for="estado" class="control-label">Nombre: </label> 
						<input type="text" id="nombre" name="nombre" value="${empleado.getNombre()}" ></input>
						<br><br>
						<label for="estado" class="control-label">Apellido: </label> 
						<input type="text" id="apellido" name="apellido" value="${empleado.getApellido()}"></input>
						<br><br>
						</div>	
						<div class="derecha">	
						<label for="estado" class="control-label">Antigüedad: </label> 
						<input type="text" id="antiguedad" name="antiguedad" value="${empleado.getAntiguedad()}"></input>
						<br><br>
						<button type="submit" class="btn btn-default">Confirmar</button>
						</div>
					</form>	
			</div>
	</div>

	</div>
</body>
	<%@ include file="/Bottom.html" %>
</html>
