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
					Descuento del Empleado
				</h1>
			</div>
			
			 <div class="2 main">
				<div></div>
				<h2 class="page-header" style="margin-top: 15px;">
					Descuentos del mes de <script>
									var meses = new Array ("Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre");
									var f=new Date();
									document.write(meses[f.getMonth()] );
									</script>
									</form>
				</h2>
			</div>
			
			<p> Nombre: ${empleado.getNombre()}</p>
			<p> Apellido: ${empleado.getApellido()}</p>
			<p> D.N.I.: ${empleado.getDni()}</p>
			<p> Total a descontar: ${descuento}</p>
			
			
			</div>
		</div>
	</div>
</body>

</html>
