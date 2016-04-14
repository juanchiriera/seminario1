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
					Baja de Empleado
				</h1>
			</div>
			
			<div class="col-xs-12" style="padding-left: 50px">
				<form class="col-sm-3 formulario" action="BajaEmpleadoServlet" id="BajaEmpleadoForm">
					<label> Nombre: ${empleado.getNombre()}</label>
					<br>
					<br>
					<label> Apellido: ${empleado.getApellido()}</label>
					<br>
					<br>
					<label> D.N.I.: ${empleado.getDni()}</label>
				</form>
				
			</div>
		</div>

		<form method="post" action="BajaEmpleadoServlet" >
			<input type="text" id="dni" name="dni" value="${empleado.getDni()}" disabled="disabled" style="visibility:hidden"></input>
			<div class="col-sm-2" style="padding-left: 50px">
				<button  type="submit" class="btn btn-default">Confirmar</button>
			</div>
		</form>
	</div>
</body>
	<%@ include file="/Bottom.html" %>
</html>
